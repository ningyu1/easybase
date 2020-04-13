package io.ningyu.core.common.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import io.ningyu.core.common.dto.LogInfo;
import io.ningyu.core.common.dto.QueryLog;
import io.ningyu.core.common.entity.Log;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface ILogService extends IService<Log> {

    /**
     * 查询日志列表
     * @param page
     * @param log
     * @return
     */
    Page<LogInfo> selectLogList(Page<LogInfo> page, QueryLog log);

}
