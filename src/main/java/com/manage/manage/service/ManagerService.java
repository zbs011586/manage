package com.manage.manage.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manage.manage.bean.Advice;
import com.manage.manage.bean.Image;
import com.manage.manage.bean.Manager;
import com.manage.manage.bean.Notice;
import com.manage.manage.commons.Constants;
import com.manage.manage.commons.HttpResponse;
import com.manage.manage.dao.AdviceDao;
import com.manage.manage.dao.ImageDao;
import com.manage.manage.dao.ManagerDao;
import com.manage.manage.dao.NoticeDao;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    private NoticeDao noticeDao;

    @Autowired
    private AdviceDao adviceDao;

    @Autowired
    private ImageDao imageDao;

    public HttpResponse imageSet(int id, int sort) {
        if (sort == 1) {
            Image image1 = imageDao.queryBySort(1);
            Image image2 = imageDao.queryBySort(2);
            if (image1 != null && image2 != null) {
                imageDao.updateSort3();
            }
            if (image1 != null){
                imageDao.updateSort1();
            }
            imageDao.updateSort(id, 0, 1);
        } else if (sort == 2) {
            Image image2 = imageDao.queryBySort(2);
            if (image2 != null) {
                imageDao.updateSort3();
                imageDao.updateSort2();
            }
            imageDao.updateSort(id, 0, 2);
        } else if (sort == 3) {
            imageDao.updateSort3();
            imageDao.updateSort(id, 0, 3);
        } else if (sort == 10) {
            imageDao.updateSort(id, 1, 10);
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
        return HttpResponse.OK("新增通知内容成功");
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
