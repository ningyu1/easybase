package io.ningyu.system.warehouse.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.ningyu.common.annotation.auth.NoAuthentication;
import io.ningyu.common.annotation.log.AroundLog;
import io.ningyu.common.base.PageResult;
import io.ningyu.common.base.Result;
import io.ningyu.system.warehouse.entity.WarehouseEntity;
import io.ningyu.system.warehouse.service.IWarehouseService;
import io.ningyu.system.warehouse.dto.QueryWarehouse;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 仓库 前端控制器
 * </p>
 *
 * @author ningyu
 * @since 2020-04-04
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    /**
     * 添加仓库
     */
    @PostMapping("/create")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "添加仓库")
    @AroundLog(name = "添加仓库")
    public Result<QueryWarehouse> create(@RequestBody QueryWarehouse vo) {
        WarehouseEntity entity = new WarehouseEntity();
        BeanCopier.create(QueryWarehouse.class, WarehouseEntity.class, false).copy(vo, entity, null);
        boolean result = warehouseService.save(entity);
        if (result) {
            vo.setId(entity.getId());
            return new Result<QueryWarehouse>().success("添加成功").put(vo);
        } else {
            return new Result<QueryWarehouse>().error("添加失败，请重试");
        }
    }

    /**
     * 修改仓库
     */
    @PostMapping("/update")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "更新仓库")
    @AroundLog(name = "更新仓库")
    public Result<QueryWarehouse> update(@RequestBody QueryWarehouse vo) {
        WarehouseEntity entity = new WarehouseEntity();
        BeanCopier.create(QueryWarehouse.class, WarehouseEntity.class, false).copy(vo, entity, null);
        boolean result = warehouseService.updateById(entity);
        if (result) {
            return new Result<QueryWarehouse>().success("修改成功").put(vo);
        } else {
            return new Result<QueryWarehouse>().error("修改失败");
        }
    }

    /**
     * 删除仓库
     */
    @PostMapping("/delete/{id}")
    @RequiresRoles("SYSADMIN")
    @ApiOperation(value = "删除仓库")
    @AroundLog(name = "删除仓库")
    @ApiImplicitParam(paramType = "path", name = "id", value = "仓库ID", required = true, dataType = "Integer")
    public Result<?> delete(@PathVariable("id") Integer id) {
        QueryWrapper<WarehouseEntity> qw = new QueryWrapper<WarehouseEntity>();
        qw.and(e -> e.eq("id", id));
        boolean result = warehouseService.remove(qw);
        if (result) {
            return new Result<>().success("删除成功").put(Boolean.TRUE);
        } else {
            return new Result<>().error("删除失败").put(Boolean.FALSE);
        }
    }

    /**
     * 通过id获取仓库
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    @NoAuthentication
    @ApiOperation(value = "查询仓库", notes = "查询仓库")
    @ApiImplicitParam(paramType = "query", name = "id", value = "仓库id", required = true, dataType = "Integer")
    public Result<QueryWarehouse> queryDictList(@PathVariable("id") Integer id) {
        WarehouseEntity entity = warehouseService.getById(id);
        QueryWarehouse vo = new QueryWarehouse();
        if (entity != null) {
            BeanCopier.create(WarehouseEntity.class, QueryWarehouse.class, false).copy(entity, vo, null);
        }
        return new Result<QueryWarehouse>().success().put(vo);
    }

    /**
     * 查询仓库列表
     *
     * @param vo
     * @param page
     * @return
     */
    @GetMapping(value = "/list")
    @NoAuthentication
    @ApiOperation(value = "查询仓库列表", notes = "查询仓库列表")
    public PageResult<WarehouseEntity> list(QueryWarehouse vo, Page<WarehouseEntity> page) {
        Page<WarehouseEntity> pageList = warehouseService.selectList(page, vo);
        PageResult<WarehouseEntity> pageResult = new PageResult<WarehouseEntity>(pageList.getTotal(), pageList.getRecords());
        return pageResult;
    }

}
