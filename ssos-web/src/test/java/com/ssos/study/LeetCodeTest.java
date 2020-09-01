package com.ssos.study;

/**
 * @ClassName: LeetCodeTest
 * @Description: dto
 * @Author: xwl
 * @Date: 2019/10/31 12:28 下午
 * @Vsersion: 1.0
 */
public class LeetCodeTest {


    public static void main(String[] args) {
        String s = "aacaabba";
        System.out.println(test(s));
    }

    public static String test(String s) {
        int size = 0;
        int[] ss = {};
        int length = s.length();
        char[] chars = s.toCharArray();
        for (int i = 1; i < length; i++) {
            if (i - 1 != -1 && i + 1 < length) {
                int[] aaa = aaa(chars, 1, i, length);
                if (aaa.length > 0 && Math.max(aaa[1] - aaa[0], size) != size)
                    ss = aaa;
            }
        }
        if (ss.length > 0) {
            return s.substring(ss[0], ss[1]);
        }
        return "";
    }

    public static int[] aaa(char[] chars, int iteration, int i, int length) {
        if ((i - iteration) != 0 && (i + iteration) < length) {
            if (chars[i - 1] == chars[i + 1]) {
                int[] aaa = aaa(chars, ++iteration, i, length);
                if (aaa.length > 0)
                    return aaa;
                return new int[]{i - iteration + 1, i + iteration};

            }
        }
        return new int[]{};

    }




}
