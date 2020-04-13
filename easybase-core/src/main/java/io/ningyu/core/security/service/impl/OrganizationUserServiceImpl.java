package io.ningyu.core.security.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ningyu.core.security.entity.OrganizationUser;
import io.ningyu.core.security.mapper.OrganizationUserMapper;
import io.ningyu.core.security.service.IOrganizationUserService;
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
