package com.zq;

/**
 * ascii to integer
 */
public class atoi {
    public static void main(String[] args) {
        System.out.println(new atoi().convertStr2Int("-+1"));
    }

    public int convertStr2Int(String str) {
        int result = 0;
        if (str == null) return result;
        String leftTrimStr = leftTrim(str);
        if ("".equals(leftTrimStr)) return result;
        int beginIndex = 0;
        int multi = 1;
        int upperBound = (int) (Math.pow(2, 31) - 1);
        int lowerBound = (int) (-1 * Math.pow(2, 31));
        char firstChar = leftTrimStr.charAt(0);
        if (firstChar == '+' || firstChar == '-') {
            beginIndex = 1;
            if (firstChar == '-') multi = -1;
        }
        boolean mayOverflow = false;
        int len = leftTrimStr.length();
        for (int i = beginIndex; i < len; i++) {
            char c = leftTrimStr.charAt(i);
            int num = c - '0';
            if (num >= 0 && num < 10) {
                if (!mayOverflow) {
                    if (multi == -1) result = result * 10 - num;
                    else result = result * 10 + num;
                    if (i != len - 1)
                        if (upperBound / 10 - result <= 0 || lowerBound / 10 - result >= 0)
                            mayOverflow = true;
                } else {
                    if (upperBound / 10 - result == 0) {
                        if (num < 8) return result * 10 + num;
                        else return upperBound;
                    } else if (lowerBound / 10 - result == 0) {
                        if (num <= 8) return result * 10 - num;
                        else return lowerBound;
                    } else {
                        if (result > 0) return upperBound;
                        else return lowerBound;
                    }
                }
            } else {
                //  throw new IllegalArgumentException("非法参数：" + c);
                return result;
            }
        }
        return result;
    }

    /**
     * 删除开头的空白符
     *
     * @param str
     * @return
     */
    public String leftTrim(String str) {
        int len = str.length();
        int index = 0;
        for (int i = 0; i < len; i++) {
            char c = str.charAt(i);
            if (c != ' ') {
                index = i;
                break;
            }
        }
        return str.substring(index, len);
    }
}
