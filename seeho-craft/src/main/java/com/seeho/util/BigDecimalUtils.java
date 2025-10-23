package com.seeho.util;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public final class BigDecimalUtils {

    private BigDecimalUtils() {

    }

    /**
     * @param num1
     * @param num2
     * @return num1<num2 return true
     */
    public static boolean lessThan(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2) == -1;
    }

    /**
     * @param num1
     * @param num2
     * @return num1<= um2 return true
     */
    public static boolean lessEqual(BigDecimal num1, BigDecimal num2) {
        return (num1.compareTo(num2) == -1) || (num1.compareTo(num2) == 0);
    }

    /**
     * 判断num1是否大于num2
     *
     * @param num1
     * @param num2
     * @return num1大于num2返回true
     */
    public static boolean greaterThan(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2) == 1;
    }

    /**
     * 判断num1是否大于等于num2
     *
     * @param num1
     * @param num2
     * @return num1大于或者等于num2返回true
     */
    public static boolean greaterEqual(BigDecimal num1, BigDecimal num2) {
        return (num1.compareTo(num2) == 1) || (num1.compareTo(num2) == 0);
    }

    /**
     * 判断num1是否等于num2
     *
     * @param num1
     * @param num2
     * @return num1等于num2返回true
     */
    public static boolean equal(BigDecimal num1, BigDecimal num2) {
        return num1.compareTo(num2) == 0;
    }

    /**
     * 验证数字是否最多保留两位小数
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        // 判断小数点后2位的数字的正则表达式
        Pattern pattern = Pattern.compile("^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
        Matcher match = pattern.matcher(str);
        if (match.matches() == false) {
            return false;
        } else {
            return true;
        }
    }

    public static String formatToPercent(BigDecimal num, int decimal) {
        NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(decimal);
        String format = percent.format(num.doubleValue());
        return format;
    }
}
