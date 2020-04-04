package io.ningyu.system.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ningyu.system.security.entity.OrganizationUser;
import io.ningyu.system.security.mapper.OrganizationUserMapper;
import io.ningyu.system.security.service.IOrganizationUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 */
@Service
public class OrganizationUserServiceImpl extends ServiceImpl<OrganizationUserMapper, OrganizationUser>
        implements IOrganizationUserService {
}
