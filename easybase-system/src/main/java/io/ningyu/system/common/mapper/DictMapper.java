package io.ningyu.system.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ningyu.system.common.vo.DictInfo;
import io.ningyu.system.common.entity.Dict;

import java.util.List;

/**
 * <p>
 * 数据字典表 Mapper 接口
 * </p>
 *
 */
public interface DictMapper extends BaseMapper<Dict> {
    /**
     * 查询字典列表
     * @param parentId
     * @return
     */
    List<DictInfo> queryDictTreeProc(Integer parentId);
}
