package com.demo.common.util;

public class NumberUtil {
    private final static String[] lowerNumStrArr = {"零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
    private final static String[] upperNumStrArr = {"零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};

    public static String toLowerChinese(int n) {
        return lowerNumStrArr[n];
    }

    public static String toUpperChinese(int n) {
        return upperNumStrArr[n];
    }
}
