package com.code.common.createtree;

import com.code.common.createtree.demo1;
import lombok.Data;

import java.util.List;

@Data
public class outdemo {
    private String id;
    private String parentId;
    private String name;
    private String age;
    private List<demo1> children;
}
