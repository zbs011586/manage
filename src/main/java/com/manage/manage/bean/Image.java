package com.manage.manage.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Table;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "t_image")
public class Image {

    private Integer id;

    private String path;

    private Integer sort;

    private Integer status;

    private Date createTime;

    private String url;
}
