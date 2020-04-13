package io.ningyu.core.common.service;

import io.ningyu.core.common.domain.AccessToken;

/**
 *
 */
public interface IWechatTokenService {

    /**
     * 
     * getAccessToken(从缓存获取微信的AccessToken)
     *
     * @Title: getAccessToken
     * @Description: 因为微信的accessToken有效期为2个小时，所以放到缓存，2小时取一次
     * @param appId
     * @param appSecret
     * @return
     */
    String getAccessToken(String appId, String appSecret);

    /**
     * 
     * queryAccessToken(获取微信的AccessToken)
     *
     * @Title: queryAccessToken
     * @param appId
     * @param appSecret
     * @return
     */
    AccessToken queryAccessToken(String appId, String appSecret);

}
