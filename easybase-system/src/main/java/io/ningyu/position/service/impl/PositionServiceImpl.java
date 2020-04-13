package io.ningyu.position.service.impl;

import io.ningyu.position.entity.PositionEntity;
import io.ningyu.position.dto.QueryPosition;
import io.ningyu.position.mapper.PositionMapper;
import io.ningyu.position.service.IPositionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ningyu
 * @since 2020-04-13
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionMapper, PositionEntity> implements IPositionService {


    @Autowired
    PositionMapper mapper;

    @Override
    public Page<PositionEntity> selectList(Page<PositionEntity> page, QueryPosition param) {
        Page<PositionEntity> list = mapper.selectList(page, param);
        return list;
    }
}
