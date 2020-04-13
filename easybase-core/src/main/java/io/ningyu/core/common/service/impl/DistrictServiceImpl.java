package io.ningyu.core.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ningyu.core.common.entity.District;
import io.ningyu.core.common.mapper.DistrictMapper;
import io.ningyu.core.common.service.IDistrictService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements IDistrictService {
}
