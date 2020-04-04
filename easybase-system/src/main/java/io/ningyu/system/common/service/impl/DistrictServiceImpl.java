package io.ningyu.system.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ningyu.system.common.entity.District;
import io.ningyu.system.common.mapper.DistrictMapper;
import io.ningyu.system.common.service.IDistrictService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 */
@Service
public class DistrictServiceImpl extends ServiceImpl<DistrictMapper, District> implements IDistrictService {
}
