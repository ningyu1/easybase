package io.ningyu.core.common.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.ningyu.core.common.dto.DictInfo;
import io.ningyu.core.common.entity.Dict;

import java.util.List;

/**
 * <p>
 * 数据字典表 服务类
 * </p>
 *
 */
public interface IDictService extends IService<Dict> {
    
    /**
     * 创建数据字典
     * 
     * @param dict
     * @param
     * @return
     */
    boolean createDict(Dict dict);

    /**
     * 更新数据字典
     * 
     * @param dict
     * @param
     * @return
     */
    boolean updateDict(Dict dict);
    
    /**
     * 删除数据字典
     * @param dictId
     * @return
     */
    boolean deleteDict(Integer dictId);
    
    /**
     * 查询字典列表值
     * @param dictCode
     * @return
     */
    List<Dict> queryDictListByPanentCode(String dictCode);
    
    /**
     * 查询字典列表树
     * @param parentId
     * @return
     */
    List<DictInfo> queryDictTreeByPanentId(Integer parentId);
}
