package io.ningyu.system.common.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * <p>
 * 数据字典
 * </p>
 *
 */
@ApiModel(value="Dict对象", description="数据字典")
public class UpdateDict implements Serializable {

    private static final long serialVersionUID = 2154482874274306219L;

    @ApiModelProperty(value = "主键id")
    private Integer id;

    @ApiModelProperty(value = "字典类型")
    private Integer parentId;

    @ApiModelProperty(value = "字典名称")
    private String dictName;

    @ApiModelProperty(value = "字典值")
    private String dictCode;

    @ApiModelProperty(value = "排序")
    private Integer dictOrder;

    @ApiModelProperty(value = "1有效，0禁用")
    private String dictStatus;

    @ApiModelProperty(value = "描述信息")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    
    public Integer getParentId() {
        return parentId;
    }

    
    
    public String getDictName() {
        return dictName;
    }

    
    public void setDictName(String dictName) {
        this.dictName = dictName;
    }

    
    public String getDictCode() {
        return dictCode;
    }

    
    public void setDictCode(String dictCode) {
        this.dictCode = dictCode;
    }

    
    public Integer getDictOrder() {
        return dictOrder;
    }

    
    public void setDictOrder(Integer dictOrder) {
        this.dictOrder = dictOrder;
    }

    
    public String getDictStatus() {
        return dictStatus;
    }

    
    public void setDictStatus(String dictStatus) {
        this.dictStatus = dictStatus;
    }

    
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Dict{" +
        " id=" + id +
        ", parentId=" + parentId +
        ", dictName=" + dictName +
        ", dictCode=" + dictCode +
        ", dictOrder=" + dictOrder +
        ", dictStatus=" + dictStatus +
        ", description=" + description +
        "}";
    }
}
