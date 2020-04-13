package io.ningyu.core.common.controller;

import io.ningyu.common.annotation.auth.NoAuthentication;
import io.ningyu.common.base.Result;
import io.ningyu.core.common.dto.WeiXinJsUrl;
import io.ningyu.core.common.service.IWechatApiService;
import io.ningyu.core.common.util.WeiXinSign;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信
 */
//@RestController
//@RequestMapping("/weixin")
public class WeiXinApiController
{
    @Value("${weixin.api.appId}")
    private String appId;

    @Value("${weixin.api.secret}")
    private String appSecret;

    @Resource
    private IWechatApiService weiXinApiService;
    
    
    @PostMapping("/js/config")
    @NoAuthentication
    public Result<Map<String, String>> jsConfig(@RequestBody WeiXinJsUrl jsUrl) {
        String ticket = weiXinApiService.getJsapiTicket(appId, appSecret);
        Map<String, String> signMap = WeiXinSign.sign(ticket, jsUrl.getUrl());
        signMap.put("appid", appId);
        return new Result<Map<String, String>>().success().put(signMap);
    }
}
