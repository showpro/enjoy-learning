package Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description 自定义一个运行时注解
 *
 * 注解类里面，只支持基本类型、String及枚举类型，里面所有属性被定义成方法，并允许提供默认值。
 *
 * @Target  — —注解用于什么地方
 *
 *     TYPE,  //给类（型）注解
 *     FIELD, //给字段注解，不要忘了，字段可以是对象
 *     METHOD, //给方法注解
 *     PARAMETER, //给参数注解
 *     CONSTRUCTOR, //给构造方法注解
 *     LOCAL_VARIABLE, //给局部变量注解
 *     ANNOTATION_TYPE,//给注解注解（这貌似把自己不当类来看）
 *     PACKAGE, //给包注解
 *
 *    @Retention — —注解运行状态
 *
 *    SOURCE, //源码状态运行，
 *    CLASS, //编译类文件时运行
 *    RUNTIME //运行时运行
 *
 *    @Documented — — 生成说明文档，添加类的解释 
 *
 *    @Inherited — —允许子类继承父类中的注解。
 *
 * @Author zhanzhan
 * @Date 2021/3/30 10:35
 */
@Target(ElementType.TYPE) //给类注解
@Retention(RetentionPolicy.RUNTIME)
public @interface HelloAnnotation {

    String say() default "Hi";
}
