package io.ningyu.generator;

import lombok.Builder;
import lombok.Getter;

/**
 * <功能描述>
 *
 * @author ningyu
 * @date 2020/4/13 20:10
 */
@Builder
@Getter
public class Generator {
    /**
     * 作者
     */
    String author;
    /**
     * 表明
     */
    String[] tables;
    /**
     * 表前缀
     */
    String[] tablePrefix;
    /**
     * 包名
     */
    String basePackage;
    /**
     * 模块名
     */
    String moduleName;
    /**
     * 后端代码路径
     */
    String backendOutPutDir;
    /**
     * 前端代码路径
     */
    String frontendOutPutDir;
}
