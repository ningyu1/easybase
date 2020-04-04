package io.ningyu.system.common.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.ningyu.system.common.vo.LogInfo;
import io.ningyu.system.common.vo.QueryLog;
import io.ningyu.system.common.entity.Log;
import io.ningyu.system.common.mapper.LogMapper;
import io.ningyu.system.common.service.ILogService;
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
