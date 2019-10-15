package com.manage.manage.dao;

import com.manage.manage.bean.Image;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ImageDao extends Mapper<Image> {

    List<Image> imageList();

}
