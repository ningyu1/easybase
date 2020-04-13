package io.ningyu.core.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.ningyu.common.base.BusinessException;
import io.ningyu.common.base.Constant;
import io.ningyu.core.common.domain.UserInfoWithCredentials;
import io.ningyu.core.common.service.IWechatAppService;
import io.ningyu.core.common.util.WeiXinSign;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 */
//@Service
public class WechatAppServiceImpl implements IWechatAppService {

    /**
     * 日志记录
     */
    private static final Logger logger = LoggerFactory.getLogger(WechatAppServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;

    @Value("${weixin.app.appId}")
    private String wxAppId;

    @Value("${weixin.app.secret}")
    private String wxAppSecret;

    @Value("${weixin.app.queryUserInfoUrl}")
    private String queryUserInfoUrl;

    @Override
    public String queryUserInfo(String code, UserInfoWithCredentials userInfo) {
        String openId = "";
        try {
            logger.info("获取openID时code=" + code + "appId=" + wxAppId + "secret=" + wxAppSecret);
            ResponseEntity<String> returnStr = restTemplate.getForEntity(queryUserInfoUrl, String.class, wxAppId,
            		wxAppSecret, code);
            JSONObject obj = JSON.parseObject(returnStr.getBody());
            if (null == obj || StringUtils.isEmpty(obj.getString(Constant.WEI_XIN_OPENID))) {
                throw new BusinessException("登录失败");
            }
            // 验证用户信息完整性
            String sha1 = WeiXinSign.getSha1(userInfo.getRawData() + obj.getString("session_key"));
//            if (!userInfo.getSignature().equals(sha1)) {
//                throw new BusinessException("登录失败");
//            }
            String returnId = (String) obj.get(Constant.WEI_XIN_OPENID);
            if (!StringUtils.isEmpty(returnId)) {
                openId = returnId;
            }
        } catch (Exception e) {
            logger.error("调用获取微信queryAppId接口异常：" + e);
        }
        logger.info("获取openID时openid=" + openId);
        return openId;
    }
}
