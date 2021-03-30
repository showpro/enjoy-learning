package Annotation;

import java.lang.annotation.Annotation;

/**
 * @Description 解析注解
 *
 * 注解本质是一个继承了Annotation的特殊接口，其具体实现类是Java运行时生成的动态代理对象$Proxy1，该类就是HelloAnnotation注解（接口）的具体实现类。
 * 通过代理对象调用自定义注解（接口）的方法，会最终调用AnnotationInvocationHandler的invoke方法。
 * 该方法会从memberValues这个Map（key="say",value="Do it"）中索引出对应的值。而memberValues的来源是Java中TestMain的常量池。
 *
 * @Author zhanzhan
 * @Date 2021/3/30 10:39
 */
@HelloAnnotation(say = "Do it!")
public class TestMain {
    public static void main(String[] args) throws ClassNotFoundException {

        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //获取TestMain类上的注解对象
        HelloAnnotation annotation = TestMain.class.getAnnotation(HelloAnnotation.class);
        //调用注解对象的say方法，并打印到控制台。通过打断点可以看到HelloAnnotation注解的实例是jvm生成的动态代理类的对象。
        System.out.println(annotation.say());
        /**
         * 这个运行时生成的动态代理对象是可以导出到文件的，方法有两种
         *
         * 1、在代码中加入System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
         * 2、在运行时加入jvm 参数 -Dsun.misc.ProxyGenerator.saveGeneratedFiles=true
         * 这里使用第一种，
         */

//        Class<?> testMainClass = Class.forName("Annotation.TestMain");
//        Annotation[] annotations = testMainClass.getAnnotations();
//        for (Annotation annotation1 : annotations) {
//            HelloAnnotation helloAnnotation = (HelloAnnotation)annotation1;
//            System.out.println(annotation.say());
//        }

    }
}
