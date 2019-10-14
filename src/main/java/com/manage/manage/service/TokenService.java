package com.manage.manage.service;

import com.manage.manage.bean.Token;
import com.manage.manage.commons.CharUtil;
import com.manage.manage.dao.TokenDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class TokenService {
    @Autowired
    private TokenDao tokenDao;
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    public Token queryByUserId(Integer userId) {
        Token token = new Token();
        token.setUserId(userId);
        return tokenDao.selectOne(token);
    }

    public Token queryByToken(String token) {
        Token param = new Token();
        param.setToken(token);
        return tokenDao.selectOne(param);
    }

    public void save(Token token) {
        tokenDao.insert(token);
    }

    public void update(Token token) {
        tokenDao.updateByPrimaryKey(token);
    }

    public Map<String, Object> createToken(Integer userId) {
        //生成一个token
        String token = CharUtil.getRandomString(32);
        //当前时间
        Date now = new Date();

        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        Token tokenEntity = queryByUserId(userId);
        if (tokenEntity == null) {
            tokenEntity = new Token();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            save(tokenEntity);
        } else {
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            update(tokenEntity);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", EXPIRE);

        return map;
    }
}
