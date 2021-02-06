package com.code.module.base.utils;

import cn.hutool.core.util.StrUtil;

public class ConvertStringUtils {
    //转换成大驼峰命名格式
    public static String convertBigHump(String input) {
        if (StrUtil.isEmpty(input)) {
            return null;
        }
        String[] inputArray = input.split("_");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < inputArray.length; i++) {
            String str = inputArray[i];
            if (str.length() > 1) {
                char ch = str.charAt(0);
                String sb = charToBigString(ch) + StrUtil.sub(str, 1, str.length()).toLowerCase();
                builder.append(sb);
            } else if (str.length() == 1) {
                String sb = str.toUpperCase();
                builder.append(sb);
            }
        }
        return builder.toString();
    }

    //转换成小驼峰命名格式
    public static String convertSmallHump(String input) {
        if (StrUtil.isEmpty(input)) {
            return null;
        }
        String[] inputArray = input.split("_");
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < inputArray.length; i++) {
            String str = inputArray[i];
            if (i == 0) {
                String sb = str.toLowerCase();
                builder.append(sb);
                continue;
            }
            if (str.length() > 1) {
                char ch = str.charAt(0);
                String sb = charToBigString(ch) + StrUtil.sub(str, 1, str.length()).toLowerCase();
                builder.append(sb);
            } else if (str.length() == 1) {
                String sb = str.toUpperCase();
                builder.append(sb);
            }
        }
        return builder.toString();
    }

    public static String charToBigString(char ch) {
        String sch = String.valueOf(ch).toUpperCase();
        return sch;
    }

    //驼峰命名转化成下划线命名
    public static String humpToUnderline(String input) {
        if (StrUtil.isEmpty(input)) {
            return null;
        }
        char[] chars = input.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {

            char aChar = chars[i];
            if (Character.isUpperCase(aChar)) {
                if (i == 0) {
                    builder.append(aChar);
                    continue;
                }
                builder.append("_");
                builder.append(aChar);
            } else {
                builder.append(aChar);
            }
        }
        return builder.toString();
    }

}