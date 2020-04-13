package io.ningyu.core.common.service;

import io.ningyu.core.common.domain.UserInfoWithCredentials;

/**
 *
 */
public interface IWechatAppService {

    /**
     * 获取用户信息
     * @param code
     * @param userInfo
     * @return
     */
    String queryUserInfo(String code, UserInfoWithCredentials userInfo);
}
