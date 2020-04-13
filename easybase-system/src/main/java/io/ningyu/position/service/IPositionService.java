package io.ningyu.position.service;

import io.ningyu.position.entity.PositionEntity;
import io.ningyu.position.dto.QueryPosition;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ningyu
 * @since 2020-04-13
 */
public interface IPositionService extends IService<PositionEntity> {

    Page<PositionEntity> selectList(Page<PositionEntity> page, QueryPosition param);

}
