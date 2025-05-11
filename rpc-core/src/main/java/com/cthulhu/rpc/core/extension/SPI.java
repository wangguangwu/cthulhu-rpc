package com.cthulhu.rpc.core.extension;

import java.lang.annotation.*;

/**
 * SPI注解，用于标记一个接口是可扩展的服务接口
 * 
 * @author cthulhu-rpc
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface SPI {
    
    /**
     * 默认的扩展实现名称
     * 
     * @return 默认扩展名
     */
    String value() default "";
}
