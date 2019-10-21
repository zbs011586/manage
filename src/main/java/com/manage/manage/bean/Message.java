package com.manage.manage.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * @description 促销活动信息表
 * @author 张宝帅
 * @date 2019/9/16 15:48
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "t_message")
public class Message {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer messageId;

    private Integer userId;

    private String userName;

    private String avatar;

    private String title;

    private String content;

    private String province;

    private String city;

    private String district;

    private String address;

    private Double lon;

    private Double lat;

    private Integer adcode;//消息所属行政区域

    private Integer shopStatus;

    @Transient
    private double distance;

    private Integer rebate;//折扣大小

    private String classify;//分类

    private String filePath;//上传文件路径

    @Transient
    private String[] filePaths;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;//创建时间

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startTime;//活动开始时间

    @Transient
    private int likeStatus;

    @Transient
    private int warnStatus;

    @Transient
    private int likeCount;

    @Transient
    private int commentCount;

    @Transient
    private int warnCount;


}
