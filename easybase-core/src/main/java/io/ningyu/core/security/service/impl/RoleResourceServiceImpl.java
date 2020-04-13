package io.ningyu.core.security.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ningyu.core.security.dto.UpdateRoleResource;
import io.ningyu.core.security.entity.Resource;
import io.ningyu.core.security.entity.RoleResource;
import io.ningyu.core.security.mapper.RoleResourceMapper;
import io.ningyu.core.security.service.IResourceService;
import io.ningyu.core.security.service.IRoleResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 */
@Service
public class RoleResourceServiceImpl extends ServiceImpl<RoleResourceMapper, RoleResource>
        implements IRoleResourceService {

    @Autowired
    private IResourceService resourceService;

    @Override
    @Cacheable(value = "resources", key = "'role_id_'.concat(#roleId)")
    public List<Resource> queryResourceByRoleId(Integer roleId) {
        QueryWrapper<RoleResource> ew = new QueryWrapper<>();
        ew.eq("role_id", roleId);
        List<RoleResource> roleResourceList = this.list(ew);
        if (!CollectionUtils.isEmpty(roleResourceList)) {
            List<Integer> resourceIds = new ArrayList<Integer>();
            for (RoleResource roleResource : roleResourceList) {
                resourceIds.add(roleResource.getResourceId());
            }
            QueryWrapper<Resource> ewResource = new QueryWrapper<>();
            ewResource.in("id", resourceIds);
            List<Resource> resourceList = resourceService.list(ewResource);
            return resourceList;
        } else {
            return null;
        }
    }

    @Override
    @CacheEvict(value = "resources", allEntries = true)
    public boolean updateList(UpdateRoleResource updateRoleResource) {
        List<RoleResource> addList = updateRoleResource.getAddResources();
        if (!CollectionUtils.isEmpty(addList)) {
            this.saveBatch(addList);
        }
        List<RoleResource> delList = updateRoleResource.getDelResources();
        if (!CollectionUtils.isEmpty(delList)) {
            List<Integer> resIdList = new ArrayList<>();
            for (RoleResource rr : delList) {
                resIdList.add(rr.getResourceId());
            }
            Integer roleId = updateRoleResource.getRoleId();
            QueryWrapper<RoleResource> ewResource = new QueryWrapper<>();
            ewResource.eq("role_id", roleId).in("resource_id", resIdList);
            this.remove(ewResource);
        }
        return true;
    }
}
