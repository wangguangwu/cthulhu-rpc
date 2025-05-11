package com.cthulhu.rpc.protocol.filter;

import com.cthulhu.rpc.core.common.URL;
import com.cthulhu.rpc.core.extension.SPI;
import com.cthulhu.rpc.core.invoker.Invocation;
import com.cthulhu.rpc.core.invoker.Invoker;
import com.cthulhu.rpc.core.invoker.Result;

/**
 * 过滤器接口 - 用于在服务调用前后进行拦截处理
 *
 * @author wangguangwu
 */
@SPI
public interface Filter {
    /**
     * 执行过滤逻辑
     *
     * @param invoker 服务调用者
     * @param invocation 调用信息
     * @return 调用结果
     * @throws Exception 调用过程中的异常
     */
    Result invoke(Invoker<?> invoker, Invocation invocation) throws Exception;
}
