package com.manage.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manage.manage.bean.TestData;
import com.manage.manage.dao.TestDataDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Component
@EnableScheduling
public class Task {

    @Autowired
    private TestDataDao testDataDao;

    @Scheduled(cron = "*/5 * * * * ?")
    public void testData() {
        List<TestData> testData = testDataDao.selectAll();
        for (TestData data : testData) {
            if (data.getStatus() == 0){
                log.info("有未传递测试数据,开始进行传递");
                RestTemplate template = new RestTemplate();
                HttpHeaders httpHeaders = new HttpHeaders();
                JSONObject object = new JSONObject();
                object.put("cid",data.getCid());
                object.put("name",data.getName());
                object.put("openId",data.getOpenId());
                HttpEntity entity = new HttpEntity(object, httpHeaders);
                ResponseEntity<String> responseEntity = template.postForEntity("http://19.120.74.73:8091/yueshengshi/provident/online/test/data", entity, String.class);
                JSONObject jsonObject = JSON.parseObject(responseEntity.getBody());
                if (jsonObject.getIntValue("errcode")==0){
                    log.info("测试数据传递完毕");
                    data.setStatus(1);
                    testDataDao.updateByPrimaryKey(data);
                }
            }
        }
    }
}
