package ClassReflect;

import com.alibaba.fastjson.JSON;
import common.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;

/**
 * @Description 反射
 * @Author zhanzhan
 * @Date 2021/5/28 9:47
 */
@Slf4j
public class ReflectDemo {

    public static void main(String[] args) throws Exception {
        String cardNo = " 662343 434343 453             4533453 ";

        String s = StringUtils.trimAllWhitespace(cardNo);
        System.out.println(s);

        String trim = cardNo.replaceAll(" ", "");
        System.out.println(trim);

        // (\s*)表示连续空格的字符串
        trim = cardNo.replaceAll("\\s*", "");
        System.out.println(trim);

        //  \n:换行  \t:空格  "黄杨大道240\n\n\r\t" + "号";
        String address = "黄杨大道240\n\n\r\t" + "号";
        System.out.println(address);
        // []+ 是定义匹配的字符范围。注意后面有 *，可以为空; 注意后面有 +，不可以为空
        address = address.replaceAll("[\\r\\n\\t]+", "");
        System.out.println(address);

        Student student = new Student("张三",18,"杭州","男");
        System.out.println("==========对象修改前：" + student);
        setObjectField(student);
    }


    /**
     * 处理对象所有字段，去除转义符号
     *
     * @param obj
     */
    private void dealObjectField(Object obj) {
        Class<?> aClass = obj.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            try {
                declaredField.setAccessible(true);
                Object o = declaredField.get(obj);
                if (o instanceof String) {
                    String value = (String) o;
                    if (StringUtils.hasText(value)) {
                        value = value.replaceAll("[\\r\\n\\t]+", "");
                        declaredField.set(obj, value);
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

    private static void setObjectField(Object obj) throws Exception {
        Class<?> aClass = obj.getClass();
        //拿到所有字段属性
        Field[] declaredFields = aClass.getDeclaredFields();
        //拿到具体字段名称
        Field userName = aClass.getDeclaredField("userName");
        Field sex = aClass.getDeclaredField("sex");
        userName.setAccessible(true);
        sex.setAccessible(true);

        userName.set(obj, "李四");
        sex.set(obj,"女");
        System.out.println("==========对象修改后：" + obj);

    }

}
