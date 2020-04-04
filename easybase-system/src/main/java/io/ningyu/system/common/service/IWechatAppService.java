package io.ningyu.system.common.service;

import io.ningyu.system.common.domain.UserInfoWithCredentials;

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
