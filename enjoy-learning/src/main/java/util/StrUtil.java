package util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import net.sourceforge.pinyin4j.PinyinHelper;

//import org.apache.commons.lang.StringUtils;

/**
 * 字符串工具類
 *
 * @author Administrator
 */
public class StrUtil {

    private static final DecimalFormat moneyFormat = new DecimalFormat(",###,##0.00");

    /**
     * 过滤空NULL
     *
     * @param o
     * @return
     */
    public static String FilterNull(Object o) {
        return o != null && !"null".equals(o.toString()) ? o.toString().trim() : "";
    }

    /**
     * 是否为空
     *
     * @param o
     * @return
     */
    public static boolean isEmpty(Object o) {
        if (o == null) {
            return true;
        }
        if ("".equals(FilterNull(o.toString()))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 是否为空，list 集合时
     *
     * @param col
     * @return
     */
    public static boolean isEmpty(Collection<?> col) {
        if (null == col || col.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 是否为空，map 集合时
     *
     * @param map
     * @return
     */
    public static boolean isEmpty(Map<?, ?> map) {
        if (null == map || map.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * 是否为空，数组时
     *
     * @param objs
     * @return
     */
    public static boolean isEmpty(Object[] objs) {
        if (null == objs || objs.length == 0) {
            return true;
        }
        return false;
    }

    /**
     * 是否不为空
     *
     * @param o
     * @return
     */
    public static boolean isNotEmpty(Object o) {
        if (o == null) {
            return false;
        }
        if ("".equals(FilterNull(o.toString()))) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 是否不为空，list 集合时
     *
     * @param col
     * @return
     */
    public static boolean isNotEmpty(Collection<?> col) {
        return !isEmpty(col);
    }

    /**
     * 是否不为空，map 集合时
     *
     * @param map
     * @return
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * 是否不为空，数组时
     *
     * @param objs
     * @return
     */
    public static boolean isNotEmpty(Object[] objs) {
        return !isEmpty(objs);
    }

    /**
     * 是否是所有字段都是空的空对象（包含对象本身是空的）
     *
     * @param o
     * @return
     */
    public static boolean isAllFieldEmptyObject(Object o) {
        if (isEmpty(o)) {
            return true;
        }
        return isAllFieldNull(o);
    }

    /**
     * 是否可转化为数字
     *
     * @param o
     * @return
     */
    public static boolean isNum(Object o) {
        try {
            new BigDecimal(o.toString());
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * @param str 字符串
     * @return 是否为数字
     */
    public static boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false;
        }
        return true;
    }

    /**
     * 是否可转化为Long型数字
     *
     * @param o
     * @return
     */
    public static boolean isLong(Object o) {
        try {
            new Long(o.toString());
            return true;
        } catch (Exception e) {
        }
        return false;
    }

    /**
     * 转化为Long型数字, 不可转化时返回0
     *
     * @param o
     * @return
     */
    public static Long toLong(Object o) {
        if (isLong(o)) {
            return new Long(o.toString());
        } else {
            return 0L;
        }
    }

    /**
     * 转化为int型数字, 不可转化时返回0
     *
     * @param o
     * @return
     */
    public static int toInt(Object o) {
        if (isNum(o)) {
            return new Integer(o.toString());
        } else {
            return 0;
        }
    }

    /**
     * 按字符从左截取固定长度字符串, 防止字符串超长, 默认截取50
     *
     * @param o
     * @return
     */
    public static String holdmaxlength(Object o) {
        int maxlength = 50;
        if (o == null) {
            return "";
        }
        return subStringByByte(o, maxlength);
    }

    /**
     * 从左截取固定长度字符串, 防止字符串超长, maxlength为0时默认50
     *
     * @param o
     * @return
     */
    public static String holdmaxlength(Object o, int maxlength) {
        maxlength = maxlength <= 0 ? 50 : maxlength;
        if (o == null) {
            return "";
        }
        return subStringByByte(o, maxlength);
    }

    /**
     * 按字节截取字符串
     *
     * @param o
     * @param len
     * @return
     */
    private static String subStringByByte(Object o, int len) {
        if (o == null) {
            return "";
        }
        String str = o.toString();
        String result = null;
        if (str != null) {
            byte[] a = str.getBytes();
            if (a.length <= len) {
                result = str;
            } else if (len > 0) {
                result = new String(a, 0, len);
                int length = result.length();
                if (str.charAt(length - 1) != result.charAt(length - 1)) {
                    if (length < 2) {
                        result = null;
                    } else {
                        result = result.substring(0, length - 1);
                    }
                }
            }
        }
        return result;
    }

    /**
     * 替换字符串,支持字符串为空的情形
     *
     * @param strData
     * @param regex
     * @param replacement
     * @return
     */
    public static String replace(String strData, String regex, String replacement) {
        return strData == null ? "" : strData.replaceAll(regex, replacement);
    }

    /**
     * 字符串转为HTML显示字符
     *
     * @param strData
     * @return
     */
    public static String String2HTML(String strData) {
        if (strData == null || "".equals(strData)) {
            return "";
        }
        strData = replace(strData, "&", "&amp;");
        strData = replace(strData, "<", "&lt;");
        strData = replace(strData, ">", "&gt;");
        strData = replace(strData, "\"", "&quot;");
        return strData;
    }

    /**
     * 把异常信息转换成字符串，以方便保存
     */
    public static String getexceptionInfo(Exception e) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            e.printStackTrace(new PrintStream(baos));
        } finally {
            try {
                baos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return baos.toString();
    }

    /**
     * 过滤特殊符号
     */
    public static String regex(String str) {
        Pattern pattern = Pattern.compile("[0-9-:/ ]");// 中文汉字编码区间
        Matcher matcher;
        char[] array = str.toCharArray();
        for (int i = 0; i < array.length; i++) {
            matcher = pattern.matcher(String.valueOf(array[i]));
            if (!matcher.matches()) {// 空格暂不替换
                str = str.replace(String.valueOf(array[i]), "");// 特殊字符用空字符串替换
            }
        }

        return str;
    }

    public static String comma_insert(String commaexpress, String newelement, int index) {
        int length = commaexpress.length();
        if (index > length) {
            index = length;
        } else if (index < 0) {
            index = 0;
        }
        String result = commaexpress.substring(0, index) + newelement + commaexpress.substring(index, commaexpress.length());
        return result;
    }

    /**
     * 将"/"替换成"\"
     *
     * @param strDir
     * @return
     */
    public static String changeDirection(String strDir) {
        String s = "/";
        String a = "\\";
        if (strDir != null && !" ".equals(strDir)) {
            if (strDir.contains(s)) {
                strDir = strDir.replace(s, a);
            }
        }
        return strDir;
    }

    /**
     * 去除字符串中 头和尾的空格，中间的空格保留
     *
     * @Title: trim @Description: TODO @return String @throws
     */
    public static String trim(String s) {
        int i = s.length();// 字符串最后一个字符的位置
        int j = 0;// 字符串第一个字符
        int k = 0;// 中间变量
        char[] arrayOfChar = s.toCharArray();// 将字符串转换成字符数组
        while ((j < i) && (arrayOfChar[(k + j)] <= ' '))
            ++j;// 确定字符串前面的空格数
        while ((j < i) && (arrayOfChar[(k + i - 1)] <= ' '))
            --i;// 确定字符串后面的空格数
        return (((j > 0) || (i < s.length())) ? s.substring(j, i) : s);// 返回去除空格后的字符串
    }

    /**
     * 得到大括号中的内容
     *
     * @param str
     * @return
     */
    public static String getBrackets(String str) {
        int a = str.indexOf("{");
        int c = str.indexOf("}");
        if (a >= 0 && c >= 0 & c > a) {
            return (str.substring(a + 1, c));
        } else {
            return str;
        }
    }

    /**
     * 将字符串中所有的，替换成|
     *
     * @param str
     * @return
     */
    public static String commaToVerti(String str) {
        if (str != null && !"".equals(str) && str.contains(",")) {
            return str.replaceAll(",", "|");
        } else {
            return str;
        }
    }

    /**
     * 去掉字符串中、前、后的空格
     *
     * @param name
     * @throws IOException
     */
    public static String extractBlank(String name) {
        if (name != null && !"".equals(name)) {
            return name.replaceAll(" +", "");
        } else {
            return name;
        }
    }

    /**
     * 将null换成""
     *
     * @param str
     * @return
     */
    public static String ConvertStr(String str) {
        return str != null && !"null".equals(str) ? str.trim() : "";
    }

    /**
     * 手机号码验证
     *
     * @param phone
     * @return
     */
    public static boolean isPhone(String phone) {
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        // String regex = "^1[3456789]\\\\d{9}$";
        if (phone.length() != 11) {
            // MToast.showToast("手机号应为11位数");
            return false;
        } else {
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (!isMatch) {
                // MToast.showToast("请填入正确的手机号");
            }
            return isMatch;
        }
    }

    public static boolean isIDNumber(String IDNumber) {
        if (IDNumber == null || "".equals(IDNumber)) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        String regularExpression = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|"
                + "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";
        boolean matches = IDNumber.matches(regularExpression);

        // 判断第18位校验值
        if (matches) {

            if (IDNumber.length() == 18) {
                try {
                    char[] charArray = IDNumber.toCharArray();
                    // 前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    // 这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    if (idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase())) {
                        return true;
                    } else {
                        System.out.println("身份证最后一位:" + String.valueOf(idCardLast).toUpperCase() + "错误,正确的应该是:" + idCardY[idCardMod].toUpperCase());
                        return false;
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("异常:" + IDNumber);
                    return false;
                }
            }

        }
        return matches;
    }

    /**
     * 随机生产四位验证码
     *
     * @return
     */
    public static String generalSmsCode() {
        String str = "0123456789";
        StringBuilder sb = new StringBuilder(4);
        for (int i = 0; i < 4; i++) {
            char ch = str.charAt(new Random().nextInt(str.length()));
            sb.append(ch);
        }
        return sb.toString();

    }

    /**
     * 判断是否是字母
     *
     * @return
     * @throws IOException
     */
    public static boolean isLetter(String password) {
        if (isEmpty(password)) {
            return false;
        }
        char[] charArrayPass = password.toCharArray();
        boolean isLetter = true;
        for (char ch : charArrayPass) {
            if (!((ch >= 97 && ch <= 122) || (ch >= 65 && ch <= 90))) {
                isLetter = false;
                break;
            }
        }
        return isLetter;

    }

    /**
     * 判断参数是否为空，为空返回1 不为空返回0
     */
    public static int paramArgs(Object... args) {
        for (Object obj : args) {
            if (isEmpty(obj)) {
                return 1;
            }
        }
        return 0;
    }

    /**
     * 判断数组是否有重复v
     *
     * @param array
     * @return
     */
    public static boolean cheakIsRepeat(Integer[] array) {
        HashSet<Integer> hashSet = new HashSet<Integer>();
        for (int i = 0; i < array.length; i++) {
            hashSet.add(array[i]);
        }
        if (hashSet.size() == array.length) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断数组是否有重复v
     *
     * @param array1
     * @param array2
     * @return
     */
    public static boolean cheakIsSame(String[] array1, String[] array2) {
        Arrays.sort(array1);
        Arrays.sort(array2);
        return Arrays.equals(array1, array2);
    }

    /**
     * 判断数组是否有重复v
     *
     * @param array1
     * @param array2
     * @return
     */
    public static boolean cheakIsTheSame(Integer[] array1, Integer[] array2) {
        Arrays.sort(array1);
        Arrays.sort(array2);
        return Arrays.equals(array1, array2);
    }

    /**
     * 判断数组是否有重复
     *
     * @param array1
     * @param array2
     * @return
     */
    public static boolean cheakIsSame(Integer[] array1, Integer[] array2) {
        if (cheakIsRepeat(array1)) {// 没有重复
            return false;
        }
        boolean flag = false;
        out:
        for (int x = 0; x < array1.length; x++) {
            inner:
            for (int y = 0; y < array1.length; y++) {
                if (x == y) {
                    continue inner;
                }
                if (array1[x].equals(array1[y]) && array2[x].equals(array2[y])) {
                    flag = true;
                    break out;
                }
            }
        }
        return flag;
    }

    /**
     * 校验EMAIL格式，真为正确
     *
     * @param email
     * @return true 为格式正确 false 为格式错误
     * @author
     * @date 2017-7-19
     */
    public static boolean emailFormat(String email) {
        boolean tag = true;
        final String pattern1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        final Pattern pattern = Pattern.compile(pattern1);
        final Matcher mat = pattern.matcher(email);
        if (!mat.find()) {
            tag = false;
        }
        return tag;
    }

//	public static JSONObject parseXml(HttpServletRequest request) throws IOException, DocumentException {
//		JSONObject jsonObject = new JSONObject();
//		InputStream inputStream = request.getInputStream();
//		SAXReader reader = new SAXReader();
//		// 读取输入流
//		Document document = reader.read(inputStream);
//		// 得到xml根元素
//		Element root = document.getRootElement();
//		// 得到根元素的所有子节点
//		List<Element> elementList = root.elements();
//		// 遍历所有子节点
//		for (Element e : elementList) {
//			jsonObject.put(e.getName(), e.getText());
//		}
//		inputStream.close();
//		inputStream = null;
//		return jsonObject;
//	}

    /**
     * 获取登录用户的IP地址
     *
     * @param request
     * @return
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        if ("0:0:0:0:0:0:0:1".equals(ip)) {
            ip = "127.0.0.1";
        }
        if (ip.split(",").length > 1) {
            ip = ip.split(",")[0];
        }
        return ip;
    }

    public static String parseRequestBody(HttpServletRequest request) throws Exception {
        BufferedReader br = request.getReader();
        String str = "";
        String wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        wholeStr = wholeStr.replaceAll(" ", "");
        br.close();
        return wholeStr;
    }

    /**
     * listToTree
     * <p>
     * 方法说明
     * <p>
     * 将JSONArray数组转为树状结构
     *
     * @param <T>
     * @param list
     * @param clazz
     * @return JSONArray
     */
    public static <T> List<T> listToTree(List<T> list, Class<T> clazz) {
        JSONArray arr = JSONArray.parseArray(JSON.toJSONString(list));
        JSONArray r = new JSONArray();
        JSONObject hash = new JSONObject();
        // 将数组转为Object的形式，key为数组中的id
        for (int i = 0; i < arr.size(); i++) {
            JSONObject json = (JSONObject) arr.get(i);
            hash.put(json.getString("id"), json);
        }
        // 遍历结果集
        for (int j = 0; j < arr.size(); j++) {
            // 单条记录
            JSONObject aVal = (JSONObject) arr.get(j);
            // 在hash中取出key为单条记录中pid的值
            JSONObject hashVP = (JSONObject) hash.get(aVal.get("parentId").toString());
            // 如果记录的pid存在，则说明它有父节点，将她添加到孩子节点的集合中
            if (hashVP != null) {
                // 检查是否有child属性
                if (hashVP.get("children") != null) {
                    JSONArray ch = (JSONArray) hashVP.get("children");
                    ch.add(aVal);
                    hashVP.put("children", ch);
                } else {
                    JSONArray ch = new JSONArray();
                    ch.add(aVal);
                    hashVP.put("children", ch);
                }
            } else {
                r.add(aVal);
            }
        }
        return JSONArray.parseArray(r.toJSONString(), clazz);
    }

    /**
     * 树形结构转成list
     *
     * @param <T>
     * @param root
     * @param clazz
     * @return
     */
    public static <T> List<T> treeToList(List<T> root, Class<T> clazz) {
        List<T> list = new ArrayList<>();
        if (isEmpty(root)) {
            return list;
        }
        treeToList(list, root, clazz);
        return list;
    }

    public static <T> void treeToList(List<T> list, List<T> root, Class<T> clazz) {
        for (T t : root) {
            list.add(t);
            try {
                Method method = clazz.getMethod("getChildren", new Class[]{});
                @SuppressWarnings("unchecked")
                List<T> children = (List<T>) method.invoke(t);
                if (isEmpty(children)) {
                    continue;
                }
                treeToList(list, children, clazz);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 判断bean的所有属性是否是null
     *
     * @param obj
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static boolean isAllFieldNull(Object obj) {
        Class<? extends Object> clazz = obj.getClass();

        Method[] methods = clazz.getMethods();
        boolean flag = true;
        for (Method method : methods) {
            String methodName = method.getName();
            if (methodName.startsWith("get") || methodName.startsWith("is")) {
                Object value;
                try {
                    value = method.invoke(obj);
                    if (isNotEmpty(value)) {
                        flag = false;
                        break;
                    }
                } catch (Exception e) {
                    throw new RuntimeException();
                }
            }
        }
        return flag;

    }

    /**
     * 判断bean的自定的字段是否都不是空的
     *
     * @param obj
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    public static boolean isAllFieldNotNull(Object obj, String[] fieldNames) {
        if (isEmpty(fieldNames) || fieldNames.length == 0) {// 字段名为空
            return false;
        }
        Class<? extends Object> clazz = obj.getClass();
        Method[] methods = clazz.getMethods();
        boolean flag = true;// 默认都不是空的
        for (String fieldName : fieldNames) {
            String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            for (Method method : methods) {
                String methodName = method.getName();
                if (methodName.equals(getMethodName)) {
                    Object value;
                    try {
                        value = method.invoke(obj);
                        if (isEmpty(value)) {// 找到一个空的
                            flag = false;
                            break;
                        }
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                }
            }
        }
        return flag;

    }

    public static void main(String[] args) {
        Integer[] a = {1, 1, 3};
        Integer[] b = {1, 1, 3};
        System.out.println(cheakIsSame(a, b));
    }

    /**
     * 指定属性复制
     *
     * @param source
     * @param target
     * @param properties
     */
    public static void copyProperties(Object source, Object target, String... properties) {
        // 对象属性复制所属类所有方法
        Class<? extends Object> sourceClass = source.getClass();
        Class<? extends Object> targetClass = target.getClass();
        Method[] sourceMethods = sourceClass.getMethods();
        Method[] targetMethods = targetClass.getMethods();
        // 进行属性复制需要被调用的方法
        Method[] invokeMethods = new Method[properties.length * 2];
        // 从entityMethods(所有方法)中获取invokeMethods(需要被调用方法)
        for (int i = 0; i < properties.length; i++) {
            for (Method method : sourceMethods) {
                if (method.getName().equalsIgnoreCase("get" + properties[i]))
                    invokeMethods[i * 2 + 1] = method;
            }
            for (Method method : targetMethods) {
                if (method.getName().equalsIgnoreCase("set" + properties[i]))
                    invokeMethods[i * 2] = method;
            }
        }
        // 复制进行
        for (int i = 0; i < invokeMethods.length; i += 2) {
            try {
                // target.setMethod(carrier.getMethod());
                invokeMethods[i].invoke(target, invokeMethods[i + 1].invoke(source));
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * ori 原值；enter 传入值
     */
    public static int notWriteOne(Object ori, Object enter) {
        return StrUtil.isEmpty(ori) ? (StrUtil.isEmpty(enter) ? 0 : -1) : (StrUtil.isEmpty(enter) ? +1 : 0);
    }

    // 字符串数组转list集合
    public static List<String> arrayToList(String str[]) {
        List<String> list = new ArrayList<>();
        for (String s : str) {
            list.add(s);
        }
        return list;
    }

    /**
     * 金额转为带千位符的字符串
     *
     * @param decimal
     * @return
     */
    public static String moneyThousands(BigDecimal decimal) {
        if (decimal == null) {
            return "";
        }
        return moneyFormat.format(decimal);
    }

    /**
     * 判断是否是正确的日期(字符串)
     *
     * @param startDate
     * @param datePattern
     * @return
     */
    public static boolean isCorrectDate(String startDate, String datePattern) {
        if (startDate.length() != datePattern.length()) {
            return false;
        }
        try {
            DateUtils.dateParse(startDate, datePattern);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    /**
     * 实体类转map
     */
    public static Map<String, Object> object2Map(Object object) {
        Map<String, Object> result = new HashMap<>();
        //获得类的的属性名 数组
        Field[] fields = object.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                String name = new String(field.getName());
                result.put(name, field.get(object));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean stringIsEmpty(String string) {
        return string == null || ("").equals(string);
    }

    public static boolean stringIsNotEmpty(String string) {
        return string != null && !("").equals(string);
    }

    /**
     * 生成随机字符串a-z A-Z
     *
     * @param length 生成字符串长度
     * @return
     */
    public static String getRandomStr(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int choice = random.nextInt(2) % 2 == 0 ? 65 : 97; // 取得大写还是小写
            sb.append((char) (choice + random.nextInt(26)));
        }
        return sb.toString();
    }

    /**
     * 得到汉字字符串中文首字母
     * 比如：北京 -> B
     *
     * @param str
     * @return
     */
    public static String getFirstLetter(String str) {
        // if (StringUtils.isBlank(str)) {
        //     return null;
        // }

        String convert = "";
        char word = str.charAt(0);
        String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
        if (pinyinArray != null) {
            convert += pinyinArray[0].charAt(0);
        } else {
            convert += word;
        }
        return convert.toUpperCase();
    }

    /**
     * 剔除图片url中的空字符串
     * @param imgUrl
     * @return
     */
    public static String removeImgUrlEmptyStr(String imgUrl) {
        if (StrUtil.stringIsEmpty(imgUrl)){
            return "";
        }
        return imgUrl.replace(" ", "%20");
    }
}