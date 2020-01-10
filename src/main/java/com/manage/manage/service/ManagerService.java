package com.manage.manage.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.manage.bean.*;
import com.manage.manage.commons.Constants;
import com.manage.manage.commons.HttpResponse;
import com.manage.manage.dao.*;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private TokenDao tokenDao;

    @Autowired
    private NoticeDao noticeDao;

    @Autowired
    private AdviceDao adviceDao;

    @Autowired
    private ImageDao imageDao;

    @Autowired
    private MessageDao messageDao;

    @Autowired
    private QuestionDao questionDao;

    public HttpResponse questionList(){
        return HttpResponse.OK(questionDao.selectAll());
    }

    public HttpResponse questionDelete(Question param){
        Question one = new Question();
        one.setId(param.getId());
        Question question = questionDao.selectOne(one);
        questionDao.delete(question);
        return HttpResponse.OK("删除常见问题成功");
    }

    public HttpResponse questionUpdate(Question param){
        Question one = new Question();
        one.setId(param.getId());
        Question question = questionDao.selectOne(one);
        question.setQuestion(param.getQuestion());
        question.setAnswer(param.getAnswer());
        questionDao.updateByPrimaryKey(question);
        return HttpResponse.OK("更新常见问题成功");
    }
    public HttpResponse questionSave(Question param){
        Question question = new Question();
        question.setQuestion(param.getQuestion());
        question.setAnswer(param.getAnswer());
        question.setCreateTime(new Date());
        questionDao.insert(question);
        return HttpResponse.OK("保存常见问题成功");
    }

    public HttpResponse tokenCheck(String token){
        Token param = new Token();
        param.setToken(token);
        Token tokenOne = tokenDao.selectOne(param);
        if (tokenOne == null){
            return HttpResponse.ERROR(Constants.ErrorCode.TOKEN_ERROR,"token不存在");
        }else {
            HashMap map = new HashMap();
            map.put("userId",tokenOne.getUserId());
            return HttpResponse.OK(map);
        }
    }

    public HttpResponse messageDel(int messageId,String token){
        //调用小程序端的接口
        RestTemplate template = new RestTemplate();
        Map<String, Object> param = new HashMap<>();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("token",token);
        param.put("messageId", messageId);
        HttpEntity entity = new HttpEntity(param, httpHeaders);
        ResponseEntity<String> responseEntity = template.postForEntity("https://www.zhiko.store/api/message/del", entity, String.class);
        JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
        System.out.println(jsonObject.toJSONString());
        return HttpResponse.OK("删除成功");
    }

    public HttpResponse messageList(int pageNum,int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> maps = messageDao.messageList();
        for (Map<String, Object> map : maps) {
            //处理filePath
            String filePath = MapUtils.getString(map,"filePath");
            if (filePath != null && !"".equals(filePath)) {
                String[] filePaths = filePath.split(",");
                map.put("filePaths",filePaths);
            } else {
                map.put("filePaths",null);
            }
        }
        return HttpResponse.OK(new PageInfo(maps));
    }

    public HttpResponse imageDel(int id){
        imageDao.imageDel(id);
        return HttpResponse.OK("删除成功");
    }
    public HttpResponse imagePreview(){
        List<Image> images = imageDao.imagePreview();
        return HttpResponse.OK(images);
    }

    public HttpResponse imageSet(List<Integer> ids) {
        imageDao.imageCancel();
        for (int i = 0; i < ids.size(); i++) {
            imageDao.imageSet(ids.get(i),i+1);
        }
        return HttpResponse.OK("设定轮播图成功");
    }

    public HttpResponse imageList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Image> images = imageDao.imageList();
        return HttpResponse.OK(new PageInfo(images));
    }

    public HttpResponse adviceList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Advice> advice = adviceDao.adviceList();
        return HttpResponse.OK(new PageInfo(advice));
    }

    public HttpResponse noticeSet(int id) {
        //先把所有的通知状态都设定为未选中 status=1
        noticeDao.updateAllStatus();
        //再设定单条notice的status=0
        noticeDao.updateOneStatus(id);
        return HttpResponse.OK("设定成功");
    }

    public HttpResponse noticeList(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Notice> notices = noticeDao.queryAllNotice();
        return HttpResponse.OK(new PageInfo(notices));
    }

    public HttpResponse noticeCreate(String noticeText) {
        Notice notice = new Notice();
        notice.setNotice(noticeText);
        notice.setStatus(1);
        notice.setCreateTime(new Date());
        noticeDao.insert(notice);
        HashMap map = new HashMap();
        map.put("id",notice.getId());
        return HttpResponse.OK(map);
    }

    public HttpResponse login(String userName, String password) {
        Manager param = new Manager();
        param.setUserName(userName);
        Manager manager = managerDao.selectOne(param);
        if (manager == null) {
            return HttpResponse.ERROR(Constants.ErrorCode.LOGIN_ERROR, "用户名不存在");
        }
        if (!password.equals(manager.getPassword())) {
            return HttpResponse.ERROR(Constants.ErrorCode.LOGIN_ERROR, "密码错误");
        }
        Map<String, Object> tokenMap = tokenService.createToken(manager.getId());
        String token = MapUtils.getString(tokenMap, "token");
        HashMap map = new HashMap();
        map.put("token", token);
        map.put("userId", manager.getId());
        map.put("userName", manager.getUserName());
        return HttpResponse.OK(map);
    }
}
