package spring.test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * @author zhanzhan
 * @date 2021/4/16 22:08
 */

/**
 * 总结：
 *      一切框架设计必须考虑  扩展性！！！！！！扩展性！！！！！！扩展性！！！！！！
 *      1、抽象
 *      2、设计模式
 *
 *
 *      spring 给出了扩展
 *          1、在创建对象之前可以让你干一点事
 *          2、容器初始化之前可以干一点事
 *          3、在不同的阶段发出不同的事件，你还可以干一点事
 *          4、抽象出各个接口，让你为所欲为
 *          5、面向接口编程
 */
public class DemoTest {

    /**
     * 对象A依赖一个对象B属性，那么对象A如何使用B？有如下几种方式：
     * 1、直接 B b = new B(); //硬编码
     * 2、建一个对象工厂;
     * 3、反射，分别生成A,B对象的实例，那么如何将B对象设置进A对象的属性B中（即注入到A中）? 还是通过反射，
     *  clazz.getFields()拿到A对象所有public公有的属性，包括父类的； 注意：不能保证拿到的属性一定是有顺序的，但是内容肯定是一样的
     *  clazz.getDeclareFields()拿到A对象所有属性，不包括从父类继承的；如果想拿到父类的，可以使用getFields()，然后用A对象的get、set方法设置属性
     * 如果A没有get、set方法那该如何做呢？
     *
     * 那么spring是采用哪种呢？
     */
    @Test
    public void testField() throws Exception {
        UserController userController = new UserController();
        //拿到原始信息
        Class<? extends UserController> clazz = userController.getClass();

        //1、拿到所有字段属性
        Field[] declaredFields = clazz.getDeclaredFields();
        // //遍历  结果：private spring.test.UserService spring.test.UserController.userService
        Arrays.asList(declaredFields).stream().forEach(System.out::println);

        System.out.println("---------------华丽的分割线------------------");

        //2、如果采用getFields()方法结果是怎样呢？
        UserService userService = new UserService();
        Field userServiceField = clazz.getDeclaredField("userService");//先拿到A对象中具体的属性userService

        //(1) 通过set注入属性方式1：
        //拿到字段的名称: 结果 userService
        String name = userServiceField.getName();
        System.out.println("属性名称：" + name);
        //拼接成setUserService方法；标准的驼峰制
        name = name.substring(0,1).toUpperCase() + name.substring(1, name.length());
        String setMethodName = "set" + name;
        System.out.println("属性的set方法：" + setMethodName);
        Method method = clazz.getMethod(setMethodName, UserService.class);
        /**
         * 调用userController对象的userService属性的set方法，
         * 如果userService属性没有set方法则会报错
         */
        method.invoke(userController, userService);
        System.out.println(userController.getUserService());

        System.out.println("---------------华丽的分割线------------------");

        //(2) 通过set注入属性方式2：
        //设置访问修饰符,否则报私有属性访问的错误
        userServiceField.setAccessible(true);
        /**
         * userService属性可以没有set方法，也可以注入成功
         */
        //将UserService注入到A对象的哪个属性中
        userServiceField.set(userController, userService);//这样直接set就可以注入属性成功了吗？我们通过get打印看看结果
        System.out.println(userController.getUserService());
        //结果报错：私有属性非法访问 java.lang.IllegalAccessException: Class spring.test.DemoTest can not access a member of class spring.test.UserController with modifiers "private"
        //private 访问修饰符在编译期就放到flag标志中：ACC_Private

        //3、如何解决呢？在注入之前设置访问修饰符 userServiceField.setAccessible(true);
        //结果：spring.test.UserService@5f205aa

        System.out.println("---------------华丽的分割线------------------");

        //4、Spring的@Autowired注解的自动注入是如何做的？

        //(3) 通过自定义Autowired注入:
        //以下代码只是IOC的一部分
        //先拿到UserController所有的字段(即成员变量，也叫做属性)
        Field[] fields = clazz.getDeclaredFields();
        Stream.of(fields).forEach(field -> {//遍历：注入每个字段
            //字段名
            String fieldName = field.getName();
            //返回指定类型的元素(UserService头上加了自定义注解)的注释
            Autowired annotation = field.getAnnotation(Autowired.class);
            if (annotation != null) {//如果属性上加了注释
                //设置访问修饰符
                field.setAccessible(true);
                //拿到此Field对象所表示的字段的声明类型。比如对象有一个字段private UserService userService; 那么返回该字段的类型为UserService
                Class<?> type = field.getType();
                System.out.println("字段的声明类型:" + type);
                //拿到属性的描述信息
                try {
                    /**
                     * 思考：这个对象是直接newInstance出来的
                     * 在spring中 这个bean是定义在注解或者xml中的，如何找到？
                     */
                    Object object = type.getConstructor().newInstance();
                    //set注入
                    field.set(userController, object);
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }

            }
        });
        System.out.println(userController.getUserService());
        /**
         * 思考：这个对象是直接newInstance出来的
         * 在spring中 这个bean是定义在注解或者xml中的，如何找到？
         * 通过容器 Map
         * 需要通过class获取 或者 id获取
         *      @Autowired 找到匹配的type
         *      @Qualifier("id")  如果有多个，通过id匹配
         *      Map<String,BeanDefinenition> map;  // match id
         *      Map<Class,BeanDefinenition> map;  // match type
         *
         * 有没有一种情况能够让我们在create object之前和之后发生一些事情呢？ 考虑扩展性  proccesor
         *
         * 我们bean的定义信息可能在xml中，也可能在annotation中，那么到底在哪个里？
         * 大致流程如下
         * 1）抽象出一个接口BeanDefinetionReader，该接口的实现处理自己不同的逻辑（一个xml实现,一个annotation实现）；  这一步扩展性：可以自定义不同来源的信息
         * 2）spring调抽象接口就可以了,将bean信息保存； 在1） 2）之间也可以扩展：在实例化之前，加一个proccesor，允许用户将信息填入，而不是去读
         * 3）接着将bean的信息实例化
         *      --> 前置proccesor   这一步扩展性：在实例化之前做一些事情
         *      --> create object
         *      -->后置proccesor    这一步扩展性：在实例化之后做一些事情
         *
         */

    }
}
