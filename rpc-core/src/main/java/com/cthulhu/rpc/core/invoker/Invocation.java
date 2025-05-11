package com.cthulhu.rpc.core.invoker;

import java.util.Map;

/**
 * Invocation - 调用信息
 * 包含方法名、参数类型和参数值等调用信息
 */
public interface Invocation {
    /**
     * 获取方法名
     *
     * @return 方法名
     */
    String getMethodName();

    /**
     * 获取参数类型
     *
     * @return 参数类型数组
     */
    Class<?>[] getParameterTypes();

    /**
     * 获取参数值
     *
     * @return 参数值数组
     */
    Object[] getArguments();

    /**
     * 获取附加参数
     *
     * @return 附加参数Map
     */
    Map<String, String> getAttachments();

    /**
     * 获取调用者接口
     *
     * @return 调用者接口类型
     */
    String getServiceName();
}
