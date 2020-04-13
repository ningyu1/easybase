package io.ningyu.core.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.ningyu.core.common.dto.LogInfo;
import io.ningyu.core.common.dto.QueryLog;
import io.ningyu.core.common.entity.Log;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 */
public interface LogMapper extends BaseMapper<Log> {
    /**
     * 分页查询操作日志
     * @param page
     * @param log
     * @return logInfo
     */
    Page<LogInfo> selectLogList(Page<LogInfo> page, @Param("log") QueryLog log);
}
