package io.ningyu.core.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.ningyu.core.security.dto.UpdateDataPermission;
import io.ningyu.core.security.entity.DataPermission;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface IDataPermissionService extends IService<DataPermission> {

    /**
     * 修改用户数据权限
     * @param updateDataPermission
     * @return
     */
    boolean updateUserDataPermission(UpdateDataPermission updateDataPermission);
}
