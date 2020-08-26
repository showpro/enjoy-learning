package java8;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * @Description Java 8新特性：Optional类
 * 使用Optional类可以避免显式的null值判断（null的防御性检查），避免null导致的NPE（NullPointerException）。
 * @Author zhanzhan
 * @Date 2020/8/26 16:29
 */
public class OptionalTest {

    class User{
        Long id;
        String name;
        String age;
        User(){}
        User(Long id, String name, String age){
            this.id = id;
            this.name = name;
            this.age=age;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getAge() {
            return age;
        }
    }

    /**
     * 创建 Optional 实例
     */
    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyOptional_thenNull() {
        Optional<User> emptyOpt = Optional.empty();
        /**
         * get()方法主要用于返回包装对象的实际值，
         * 但是如果包装对象值为null，会抛出NoSuchElementException异常。
         */
        User user = emptyOpt.get();

        // 1、创建一个包装对象值为空的Optional对象
        String name = "zhangSan";
        Optional<String> optStr = Optional.empty();
        String s = optStr.get();
        System.out.println(s);

        // 2、创建包装对象值非空的Optional对象
        Optional<String> optStr1 = Optional.of(name);
        String s1 = optStr1.get();
        System.out.println(s1);

        // 3、创建包装对象值允许为空的Optional对象
        Optional<String> optStr2 = Optional.ofNullable(null);
        //判断是否不为null 不为空ture
        System.out.println(optStr2.isPresent());
        //zhangSan
        System.out.println(optStr2.get());
    }

    /**
     * 身为接口提供者
     */
    /**
     * 获取用户 一般写法
     * @param id 唯一id
     * @return 用户 可能为null,代表不存在
     */
    public User getUser(Long id) {
        if (null != id) {
            return new User();
        }
        return null;
    }

    /**
     * Optional 写法
     */
    public Optional<User> getUser1(Long id) {
        if (null != id) {
            return Optional.of(new User());
        }
        return Optional.empty();
    }

    /**
     * 身为调用者
     */


    @Test
    public void OptionalTest() {

    }

}
