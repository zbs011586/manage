package com.manage.manage.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;


/**
 * @description 用户token表
 * @author 张宝帅
 * @date 2019/8/25 21:32
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_token")
public class Token {

    //用户ID
    @Id
    private Integer userId;
    //token
    private String token;
    //过期时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date expireTime;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;

}
