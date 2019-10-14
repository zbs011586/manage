package com.manage.manage.dao;

import com.manage.manage.bean.Advice;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;


public interface AdviceDao extends Mapper<Advice> {

    List<Advice> adviceList();
}
