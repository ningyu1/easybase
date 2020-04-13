package io.ningyu.core.security.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.ningyu.core.security.dto.CreateRole;
import io.ningyu.core.security.dto.UpdateRole;
import io.ningyu.core.security.entity.Role;

/**
 * @ClassName: IRoleService
 * @Description: 角色相关操作接口
 */
public interface IRoleService extends IService<Role> {

    /**
     * 分页查询角色列表
     * @param page
     * @param role
     * @return
     */
    Page<Role> selectRoleList(Page<Role> page, Role role);

    /**
     * 创建角色
     * @param role
     * @return
     */
    boolean createRole(CreateRole role);

    /**
     * 更新角色
     * @param role
     * @return
     */
    boolean updateRole(UpdateRole role);

    /**
     * 删除角色
     * @param roleId
     * @return
     */
    boolean deleteRole(Integer roleId);
}
