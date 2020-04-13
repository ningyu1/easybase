package io.ningyu.core.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ningyu.core.common.dto.LogInfo;
import io.ningyu.core.common.dto.QueryLog;
import io.ningyu.core.common.entity.Log;
import io.ningyu.core.common.mapper.LogMapper;
import io.ningyu.core.common.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

    @Autowired
    LogMapper logMapper;

    @Override
    public Page<LogInfo> selectLogList(Page<LogInfo> page, QueryLog log) {
        Page<LogInfo> pageLogInfo = logMapper.selectLogList(page, log);
        return pageLogInfo;
    }
}
