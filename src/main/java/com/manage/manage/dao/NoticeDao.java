package com.manage.manage.dao;

import com.manage.manage.bean.Notice;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface NoticeDao extends Mapper<Notice> {

    List<Notice> queryAllNotice();

    void updateAllStatus();

    void updateOneStatus(@Param("id")int id);
}
