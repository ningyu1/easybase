package io.ningyu.position.mapper;

import io.ningyu.position.entity.PositionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import io.ningyu.position.dto.QueryPosition;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ningyu
 * @since 2020-04-13
 */
public interface PositionMapper extends BaseMapper<PositionEntity> {

    Page<PositionEntity> selectList(Page<PositionEntity> page, @Param("param") QueryPosition param);

}
