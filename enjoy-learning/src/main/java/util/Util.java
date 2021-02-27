package util;

import java.util.regex.Pattern;

/**
 * @Description 常用工具类
 * @Author zhanzhan
 * @Date 2021/2/25 10:53
 */
public class Util {

    /**
     * 正则表达式匹配
     *
     * @param regEx
     * @param str
     * @return
     */
    public static boolean regMatch(String regEx, String str) {
        Pattern pattern = Pattern.compile(regEx);
        return pattern.matcher(str).matches();
    }
}
