package io.ningyu.core.common.dto;

import java.io.Serializable;

/**
 */
public class WeiXinJsUrl implements Serializable {

    private static final long serialVersionUID = -6623195928451087014L;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;
}
