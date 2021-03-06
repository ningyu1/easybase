package io.ningyu.core.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ningyu.core.security.entity.OrganizationRole;
import io.ningyu.core.security.mapper.OrganizationRoleMapper;
import io.ningyu.core.security.service.IOrganizationRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 可以给组织权限，在该组织下的所有用户都有此权限 服务实现类
 * </p>
 *
 */
@Service
public class OrganizationRoleServiceImpl extends ServiceImpl<OrganizationRoleMapper, OrganizationRole>
        implements IOrganizationRoleService {
}
