package io.ningyu.system.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.ningyu.system.security.dto.UpdateDataPermission;
import io.ningyu.system.security.entity.DataPermission;

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
