package spring.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Spring的@Autowired注解的自动注入是如何做的
 *
 * 下面自定义一个Autowired注解
 *
 * @author zhanzhan
 * @date 2021/4/17 20:11
 */

/**
 * 从spring 容器中获取到bean信息注入，不同于xml中的properties标签<></>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Autowired {

}
