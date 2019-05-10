package com.jxak.education.utils;

import lombok.Data;

import java.util.List;
@Data
public class BaseTreeGrid {
    private String id;//节点id

    private String name;

    private String parentId;//节点父id

    private String url;

    private List<BaseTreeGrid> children;//孩子节点
}
