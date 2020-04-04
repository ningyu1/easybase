package io.ningyu.system.common.domain;

/**
 */
public class UserInfoRequest {

    private String openid;

    private String lang = "zh_CN";

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
