package com.cthulhu.rpc.core.extension;

import java.lang.annotation.*;

/**
 * 扩展点实现注解，用于标记一个类是某个SPI接口的实现
 * 
 * @author cthulhu-rpc
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Extension {
    
    /**
     * 扩展点名称，必须全局唯一
     * 
     * @return 扩展点名称
     */
    String value();
    
    /**
     * 扩展点优先级，数字越小优先级越高
     * 
     * @return 优先级
     */
    int order() default 0;
}
