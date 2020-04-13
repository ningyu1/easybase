package io.ningyu.core.common.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.ningyu.common.annotation.auth.NoAuthentication;
import io.ningyu.common.base.Result;
import io.ningyu.core.common.entity.District;
import io.ningyu.core.common.service.IDistrictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 地区字典
 * </p>
 *
 */
@RestController
@RequestMapping("/district")
public class DistrictController {

    @Autowired
    private IDistrictService districtService;

    /**
     * 查询地区
     */
    @GetMapping("/list")
    @NoAuthentication
    public Result<List<District>> list(String code) {
        Integer parentId = 0;
        if (!StringUtils.isEmpty(code)) {
            QueryWrapper<District> ew = new QueryWrapper<>();
            ew.eq("code", code);
            District district = districtService.getOne(ew);
            if (null != district) {
                parentId = district.getId();
            }
        }
        QueryWrapper<District> ewr = new QueryWrapper<>();
        ewr.eq("parent_id", parentId);
        List<District> districtList = districtService.list(ewr);
        return new Result<List<District>>().success().put(districtList);
    }
}
