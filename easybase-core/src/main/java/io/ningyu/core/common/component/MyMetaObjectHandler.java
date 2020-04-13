package io.ningyu.core.common.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

/**
 * @ClassName: MyMetaObjectHandler
 * @Description: 自定义填充公共字段
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        Object creator = getFieldValByName("creator", metaObject);
        if (null == creator && null != SecurityUtils.getSubject()) {
            String principal = (String) SecurityUtils.getSubject().getPrincipal();
            if (!StringUtils.isEmpty(principal)) {
                JSONObject userObj = JSON.parseObject(principal);
                try {
                    setFieldValByName("creator", userObj.getInteger("id"), metaObject);
                } catch (Exception e) {
                    setFieldValByName("creator", userObj.getString("userName"), metaObject);
                }
            }
        }
        Object createUser = getFieldValByName("createUser", metaObject);
        if (null == createUser && null != SecurityUtils.getSubject()) {
            String principal = (String) SecurityUtils.getSubject().getPrincipal();
            if (!StringUtils.isEmpty(principal)) {
                JSONObject userObj = JSON.parseObject(principal);
                try {
                    setFieldValByName("createUser", userObj.getInteger("id"), metaObject);
                } catch (Exception e) {
                    setFieldValByName("createUser", userObj.getString("userName"), metaObject);
                }
            }
        }
        Object createTime = getFieldValByName("createTime", metaObject);
        if (null == createTime) {
            setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        Object operator = getFieldValByName("operator", metaObject);
        if (null == operator && null != SecurityUtils.getSubject()) {
            String principal = (String) SecurityUtils.getSubject().getPrincipal();
            if (!StringUtils.isEmpty(principal)) {
                JSONObject userObj = JSON.parseObject(principal);
                try {
                    setFieldValByName("operator", userObj.getInteger("id"), metaObject);
                } catch (Exception e) {
                    setFieldValByName("operator", userObj.getString("userName"), metaObject);
                }

            }
        }
        Object updateUser = getFieldValByName("updateUser", metaObject);
        if (null == updateUser && null != SecurityUtils.getSubject()) {
            String principal = (String) SecurityUtils.getSubject().getPrincipal();
            if (!StringUtils.isEmpty(principal)) {
                JSONObject userObj = JSON.parseObject(principal);
                try {
                    setFieldValByName("updateUser", userObj.getInteger("id"), metaObject);
                } catch (Exception e) {
                    setFieldValByName("updateUser", userObj.getString("userName"), metaObject);
                }
            }
        }
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if (null == updateTime) {
            setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
    }
}
