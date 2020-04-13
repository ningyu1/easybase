package io.ningyu.position.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.ningyu.common.annotation.log.AroundLog;
import io.ningyu.common.base.PageResult;
import io.ningyu.common.base.Result;
import io.ningyu.position.entity.PositionEntity;
import io.ningyu.position.service.IPositionService;
import io.ningyu.position.dto.QueryPosition;
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

import java.util.List;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ningyu
 * @since 2020-04-13
 */
@RestController
@RequestMapping("/position")
public class PositionController {

    @Autowired
    private IPositionService PositionServiceImpl;

    /**
     * 添加
     */
    @PostMapping("/create")
    @ApiOperation(value = "添加")
    @AroundLog(name = "添加")
    public Result<QueryPosition> create(@RequestBody QueryPosition vo) {
        PositionEntity entity = new PositionEntity();
        BeanCopier.create(QueryPosition.class, PositionEntity.class, false).copy(vo, entity, null);
        boolean result = PositionServiceImpl.save(entity);
        if (result) {
            vo.setId(entity.getId());
            return new Result<QueryPosition>().success("添加成功").put(vo);
        } else {
            return new Result<QueryPosition>().error("添加失败，请重试");
        }
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation(value = "更新")
    @AroundLog(name = "更新")
    public Result<QueryPosition> update(@RequestBody QueryPosition vo) {
    PositionEntity entity = new PositionEntity();
        BeanCopier.create(QueryPosition.class, PositionEntity.class, false).copy(vo, entity, null);
        boolean result = PositionServiceImpl.updateById(entity);
        if (result) {
            return new Result<QueryPosition>().success("修改成功").put(vo);
        } else {
            return new Result<QueryPosition>().error("修改失败");
        }
    }

    /**
     * 删除
     */
    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除")
    @AroundLog(name = "删除")
    @ApiImplicitParam(paramType = "path", name = "id", value = "id", required = true, dataType = "Integer")
    public Result<?> delete(@PathVariable("id") Integer id) {
        QueryWrapper<PositionEntity> qw = new QueryWrapper<PositionEntity>();
        qw.and(e -> e.eq("id", id));
        boolean result = PositionServiceImpl.remove(qw);
        if (result) {
            return new Result<>().success("删除成功").put(Boolean.TRUE);
        } else {
            return new Result<>().error("删除失败").put(Boolean.FALSE);
        }
    }

    /**
     * 批量删除
     */
    @PostMapping("/delete")
    @ApiOperation(value = "批量删除")
    @AroundLog(name = "批量删除")
    public Result<?> delete(@RequestBody List<Integer> ids) {
        boolean result = PositionServiceImpl.removeByIds(ids);
        if (result) {
            return new Result<>().success("删除成功").put(Boolean.TRUE);
        } else {
            return new Result<>().error("删除失败").put(Boolean.FALSE);
        }
    }

    /**
     * 通过id获取详情
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/get/{id}")
    @ApiOperation(value = "查询详情", notes = "查询详情")
    @ApiImplicitParam(paramType = "query", name = "id", value = "id", required = true, dataType = "Integer")
    public Result<QueryPosition> get(@PathVariable("id") Integer id) {
        PositionEntity entity = PositionServiceImpl.getById(id);
        QueryPosition vo = new QueryPosition();
        if (entity != null) {
            BeanCopier.create(PositionEntity.class, QueryPosition.class, false).copy(entity, vo, null);
        }
        return new Result<QueryPosition>().success().put(vo);
    }

    /**
     * 查询列表
     *
     * @param vo
     * @param page
     * @return
     */
    @GetMapping(value = "/list")
    @ApiOperation(value = "查询列表", notes = "查询列表")
    public PageResult<PositionEntity> list(QueryPosition vo, Page<PositionEntity> page) {
        Page<PositionEntity> pageList = PositionServiceImpl.selectList(page, vo);
        PageResult<PositionEntity> pageResult = new PageResult<PositionEntity>(pageList.getTotal(), pageList.getRecords());
        return pageResult;
    }

}
