package io.ningyu.core.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.ningyu.core.common.dto.DictInfo;
import io.ningyu.core.common.entity.Dict;

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
