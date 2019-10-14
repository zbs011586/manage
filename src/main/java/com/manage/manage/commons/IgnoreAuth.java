package com.manage.manage.commons;

import java.lang.annotation.*;

/**
 * @description 自定义注解 忽略token验证
 * @author 张宝帅
 * @date 2019/9/1 0:11
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreAuth {

}
