package com.manage.manage.dao;

import com.manage.manage.bean.Image;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ImageDao extends Mapper<Image> {

    List<Image> imageList();

    Image queryBySort(@Param("sort")int sort);

    void updateSort(@Param("id")int id,@Param("status")int status,@Param("sort")int sort);

    void updateSort1();
    void updateSort2();
    void updateSort3();
}
