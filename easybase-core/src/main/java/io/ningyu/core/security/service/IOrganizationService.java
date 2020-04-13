package io.ningyu.core.security.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.ningyu.core.security.entity.Organization;

import java.util.List;

/**
 */
public interface IOrganizationService extends IService<Organization> {

    /**
     * 查询机构列表
     * @param parentId
     * @return
     */
    List<Organization> queryOrganizationByPanentId(Integer parentId);

    /**
     * 查询机构列表，不组装父子节点
     * @param parentId
     * @return
     */
    List<Organization> queryOrgList(Integer parentId);

    /**
     * 创建组织
     * @param organization
     * @return
     */
    boolean createOrganization(Organization organization);

    /**
     * 更新组织
     * @param organization
     * @return
     */
    boolean updateOrganization(Organization organization);

    /**
     * 删除组织
     * @param organizationId
     * @return
     */
    boolean deleteOrganization(Integer organizationId);
}
