package io.ningyu.system.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ningyu.system.security.entity.OrganizationRole;

/**
 * <p>
 * 可以给组织权限，在该组织下的所有用户都有此权限 Mapper 接口
 * </p>
 *
 */
public interface OrganizationRoleMapper extends BaseMapper<OrganizationRole> {
}
