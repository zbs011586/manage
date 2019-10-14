package com.manage.manage.service;

import com.manage.manage.bean.Manager;
import com.manage.manage.commons.Constants;
import com.manage.manage.commons.HttpResponse;
import com.manage.manage.dao.ManagerDao;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ManagerService {

    @Autowired
    private ManagerDao managerDao;

    @Autowired
    private TokenService tokenService;

    public HttpResponse login(String userName, String password){
        Manager param = new Manager();
        param.setUserName(userName);
        Manager manager = managerDao.selectOne(param);
        if (manager == null){
            return HttpResponse.ERROR(Constants.ErrorCode.LOGIN_ERROR,"用户名不存在");
        }
        if (!password.equals(manager.getPassword())){
            return HttpResponse.ERROR(Constants.ErrorCode.LOGIN_ERROR,"密码错误");
        }
        Map<String, Object> tokenMap = tokenService.createToken(manager.getId());
        String token = MapUtils.getString(tokenMap, "token");
        HashMap map = new HashMap();
        map.put("token",token);
        map.put("userId",manager.getId());
        map.put("userName",manager.getUserName());
        return HttpResponse.OK(map);
    }
}
