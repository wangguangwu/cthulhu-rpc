package com.cthulhu.rpc.core.common;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * URL - 统一资源定位符
 * 用于表示RPC服务地址和参数
 */
public class URL implements Serializable {
    private static final long serialVersionUID = -1985165475234910535L;

    private final String protocol;
    private final String username;
    private final String password;
    private final String host;
    private final int port;
    private final String path;
    private final Map<String, String> parameters;

    public URL(String protocol, String host, int port) {
        this(protocol, null, null, host, port, null, null);
    }

    public URL(String protocol, String host, int port, String path) {
        this(protocol, null, null, host, port, path, null);
    }

    public URL(String protocol, String host, int port, Map<String, String> parameters) {
        this(protocol, null, null, host, port, null, parameters);
    }

    public URL(String protocol, String host, int port, String path, Map<String, String> parameters) {
        this(protocol, null, null, host, port, path, parameters);
    }

    public URL(String protocol, String username, String password, String host, int port, String path, Map<String, String> parameters) {
        this.protocol = protocol;
        this.username = username;
        this.password = password;
        this.host = host;
        this.port = port;
        this.path = path;
        this.parameters = parameters != null ? new HashMap<>(parameters) : new HashMap<>();
    }

    public String getProtocol() {
        return protocol;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public Map<String, String> getParameters() {
        return Collections.unmodifiableMap(parameters);
    }

    public String getParameter(String key) {
        return parameters.get(key);
    }

    public String getParameter(String key, String defaultValue) {
        String value = getParameter(key);
        return value != null ? value : defaultValue;
    }

    public URL addParameter(String key, String value) {
        if (key == null || value == null) {
            return this;
        }
        Map<String, String> map = new HashMap<>(getParameters());
        map.put(key, value);
        return new URL(protocol, username, password, host, port, path, map);
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder();
        if (protocol != null && protocol.length() > 0) {
            buf.append(protocol).append("://");
        }
        if (username != null && username.length() > 0) {
            buf.append(username);
            if (password != null && password.length() > 0) {
                buf.append(":").append(password);
            }
            buf.append("@");
        }
        if (host != null && host.length() > 0) {
            buf.append(host);
            if (port > 0) {
                buf.append(":").append(port);
            }
        }
        if (path != null && path.length() > 0) {
            buf.append("/").append(path);
        }
        if (parameters.size() > 0) {
            buf.append("?");
            boolean first = true;
            for (Map.Entry<String, String> entry : parameters.entrySet()) {
                if (first) {
                    first = false;
                } else {
                    buf.append("&");
                }
                buf.append(entry.getKey()).append("=").append(entry.getValue());
            }
        }
        return buf.toString();
    }
}
