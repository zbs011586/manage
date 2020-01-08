package com.manage.manage.dao;

import com.manage.manage.bean.Image;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface ImageDao extends Mapper<Image> {

    List<Image> imageList();

    void imageSet(@Param("id")int id,@Param("sort")int sort,@Param("url") String url);

    void imageCancel();

    List<Image> imagePreview();

}
