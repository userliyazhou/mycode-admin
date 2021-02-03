package com.code.module.base.utils;

import lombok.Data;

import java.io.Serializable;
@Data
public class TreeConfigDTO implements Serializable {
    protected static final long serialVersionUID = 1L;
    //对应关系
    //源数据pojo和生成的pojo，三个字段必须一致
    // 比如源数据pojo和生成的pojo都使用id，parentId，children，那idKey=id，parentIdKey=parentId,childrenKey=children
    //rootValue为根节点parentId值，不能为null，为null报错
    private String idKey;
    private String parentIdKey;
    private String childrenKey;
    private String rootValue;

    public TreeConfigDTO(String idKey, String parentIdKey, String childrenKey, String rootValue) {
        this.idKey = idKey;
        this.parentIdKey = parentIdKey;
        this.childrenKey = childrenKey;
        this.rootValue = rootValue;
    }
}
