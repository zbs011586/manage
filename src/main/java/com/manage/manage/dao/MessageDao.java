package com.manage.manage.dao;

import com.manage.manage.bean.Message;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

public interface MessageDao extends Mapper<Message> {

    List<Map<String,Object>> messageList ();
}
