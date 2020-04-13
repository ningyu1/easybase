package io.ningyu.core.common.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.ningyu.common.base.PageResult;
import io.ningyu.core.common.dto.LogInfo;
import io.ningyu.core.common.dto.QueryLog;
import io.ningyu.core.common.service.ILogService;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 */
@RestController
@RequestMapping("/common/log")
public class LogController {
    
    @Autowired
    ILogService logService;

    /**
     * 查询所有日志
     */
    @GetMapping("/list")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "查询操作日志列表")
    public PageResult<LogInfo> list(QueryLog log, Page<LogInfo> page) {
        Page<LogInfo> pageLog = logService.selectLogList(page, log);
        PageResult<LogInfo> pageResult = new PageResult<LogInfo>(pageLog.getTotal(), pageLog.getRecords());
        return pageResult;
    }
}
