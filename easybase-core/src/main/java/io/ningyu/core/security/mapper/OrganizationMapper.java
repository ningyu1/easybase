package io.ningyu.core.security.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ningyu.core.security.entity.Organization;

import java.util.List;

/**
 * <p>
 * 组织表 Mapper 接口
 * </p>
 *
 */
public interface OrganizationMapper extends BaseMapper<Organization> {

    /**
     * 查询组织机构树
     * @param parentId
     * @return
     */
    List<Organization> queryOrganizationTreeProc(Integer parentId);
}
