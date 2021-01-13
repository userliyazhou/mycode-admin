package com.code.common.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

public class TreeBuilderUtils<T> {
    public List<T> generateTree(Class target, List<?> sourceLists, TreeConfigDTO treeConfigDTO) throws Exception {
        List<Map<String, Object>> sourceList = getObjectToMap(sourceLists);
        List<T> outputList = new ArrayList<>();
        Field[] fields = target.getDeclaredFields(); // 取得业务对象属性
        if (CollectionUtils.isEmpty(sourceList)) {
            return outputList;
        }
        generateRootNode(target, fields, sourceList, treeConfigDTO, outputList);
        System.out.println(JSON.toJSONString(outputList));
        return outputList;
    }

    //封装根节点
    void generateRootNode(Class target, Field[] fields, List<Map<String, Object>> sourceList, TreeConfigDTO treeConfigDTO, List<T> outputList) throws Exception {
        String rootValue = treeConfigDTO.getRootValue();
        String idKey = treeConfigDTO.getIdKey();
        String parentIdKey = treeConfigDTO.getParentIdKey();
        for (Map<String, Object> map : sourceList) {
            if (map.get(parentIdKey) == null || String.valueOf(map.get(parentIdKey)).equals("null")) {
                throw new Exception("parentId不能为null ,id"+map.get(idKey).toString());
            }
            if(map.get(idKey).toString().equals(map.get(parentIdKey).toString())){
                throw new Exception("id不能和parentId相同,id"+map.get(idKey).toString());
            }
            if (String.valueOf(map.get(parentIdKey)).equals(rootValue)) {
                String parentId=map.get(idKey).toString();
                T targetPojo = (T) target.newInstance(); // 创建目标pojo实例
                Set<String> mapKeys = map.keySet();
                for (String mapKey : mapKeys) {
                    Object mapValue = map.get(mapKey);//属性值
                    for (int pojoNum = 0; pojoNum < fields.length; pojoNum++) {
                        //取出pojo属性key
                        Field pojoField = fields[pojoNum];
                        String pojoKey = pojoField.getName();
                        if (mapKey.equals(pojoKey)) {
                            //给pojo赋值
                            pojoField.setAccessible(true);
                            pojoField.set(targetPojo, mapValue);
                            break;
                        }
                    }
                }
                //封装子节点
                generateChildrenNode(target,parentId,targetPojo,fields,treeConfigDTO,sourceList);
                outputList.add(targetPojo);
            }

        }

    }

    //封装子节点
    void generateChildrenNode(Class target,String id,T parentPojo, Field[] fields, TreeConfigDTO treeConfigDTO,List<Map<String, Object>> sourceList) throws IllegalAccessException, InstantiationException {
        //上一级节点的id是下一个节点的parent，下一级节点的数据放到上一级的children属性list中
        String idKey = treeConfigDTO.getIdKey();
        String parentIdKey = treeConfigDTO.getParentIdKey();
        String childrenKey = treeConfigDTO.getChildrenKey();
        List<T> childrenList = new ArrayList<>();
        if(StringUtils.isEmpty(id)){
            return;
        }
        for (Map<String, Object> map : sourceList) {
            if (String.valueOf(map.get(parentIdKey)).equals(id)) {
                T targetPojo = (T) target.newInstance(); // 创建目标pojo实例
                String parentId=map.get(idKey).toString();
                Set<String> mapKeys = map.keySet();
                for (String mapKey : mapKeys) {
                    Object mapValue = map.get(mapKey);//属性值
                    for (int pojoNum = 0; pojoNum < fields.length; pojoNum++) {
                        //取出pojo属性key
                        Field pojoField = fields[pojoNum];
                        String pojoKey = pojoField.getName();
                        if (mapKey.equals(pojoKey)) {
                            //给pojo赋值
                            pojoField.setAccessible(true);
                            pojoField.set(targetPojo, mapValue);
                            break;
                        }
                    }
                }
                generateChildrenNode(target,parentId,targetPojo,fields,treeConfigDTO,sourceList);
                childrenList.add(targetPojo);
            }

        }
        if(CollectionUtils.isNotEmpty(childrenList)){
            for (int pojoNum = 0; pojoNum < fields.length; pojoNum++) {
                //取出pojo属性key
                Field pojoField = fields[pojoNum];
                String pojoKey = pojoField.getName();
                if (childrenKey.equals(pojoKey)) {
                    //给pojo赋值
                    pojoField.setAccessible(true);
                    pojoField.set(parentPojo, childrenList);
                    break;
                }
            }
        }
        }





    public static List<Map<String, Object>> getObjectToMap(List<?> objList) throws IllegalAccessException {
        List<Map<String, Object>> sourceList = new ArrayList<>();
        for (Object obj : objList) {
            Map<String, Object> map = new HashMap<>();
            Class<?> clazz = obj.getClass();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                String fieldName = field.getName();
                Object value = field.get(obj);
                if (value == null) {
                    value = "";
                }
                map.put(fieldName, value);
            }
            sourceList.add(map);
        }
        return sourceList;
    }

}
