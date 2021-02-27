package jexl;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlContext;
import org.apache.commons.jexl2.JexlEngine;
import org.apache.commons.jexl2.MapContext;
import util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description Jexl表达式引擎,
 * Jexl表达式引擎可以应用在许多场景下，比如：校验数据，解析模板等
 * 背景：
 * 做项目突然遇到这样的需求:
 * 系统要获取多个数据源的数据，并进行处理，最后输出多个字段。
 * 字段的计算规则一般是简单的取值最多加一点条件判断。
 * 而且需要动态变动！！例如一个字段a的取值，如果a > 10的时候输出10,a <= 10则输出a。这里的10可能在一天后改成8，也可能在后天就改成了12。
 * 当然，如果只是一个数字的变动还好说，我们可以使用数据库进行存储。但是，万一哪天需求突然变成了a < 10的时候输出10,a >=10 则输出a,就需要对代码改动，再测试再发布才能到生产环境使用。
 * 一两个这样的字段还没什么，如果整个系统所依赖的字段都有这样的属性，那么我们就需要找一种方法来实现动态的加载逻辑。
 * 下面介绍的JEXL就可以解决这种问题。
 *
 * @Author zhanzhan
 * @Date 2021/2/25 10:43
 */
public class JexlEngineCustom {

    public static void main(String[] args) {
        JexlEngineCustom jexlEngineCustom = new JexlEngineCustom();
        //jexlEngineCustom.testJexlEngine1();
        //jexlEngineCustom.testJexlEngine2();
        jexlEngineCustom.testJexlEngine3();
    }

    public void testJexlEngine1() {
        //创建Context设值对象
        JexlContext context = new MapContext();
        String str = "一二三四五六七八九十";
        context.set("Util", new Util());
        context.set("str", str);
        context.set("ans", "");
        //可动态编译的表达式，这里要注意表达式中出现的所有变量,都需要事先set进JexlContext中,否则会报错
        String expressionStr = "ans = Util.regMatch(\"[\u4e00-\u9fa5]{10,}\",str)";
        //使用引擎创建表达式对象
        Expression expression = new JexlEngine().createExpression(expressionStr);
        //使用表达式对象开始计算表达式
        Object evaluate = expression.evaluate(context);
        System.out.println(evaluate);
        System.out.println(context.get("ans"));
    }

    /**
     * 表达式为列表（数组）时
     */
    public void testJexlEngine2() {
        //创建表达式引擎对象
        JexlEngine engine = new JexlEngine();
        //创建Context设值对象
        JexlContext context = new MapContext();
        //表达式，表达式可以是数组的属性，元素等
        String expressionStr = "array.size()";
        //创建一个列表
        List<Object> array = new ArrayList<Object>();
        array.add("this is an array");
        array.add(new Integer(2));
        //使用context对象将表达式中用到的值设置进去，必须是所有用到的值
        context.set("array",array);
        //使用引擎创建表达式对象
        Expression expression = engine.createExpression(expressionStr);
        //使用表达式对象开始计算表达式："array.size()" , 即计算列表大小
        Object evaluate = expression.evaluate(context);
        //输出：2
        System.out.println(evaluate);
    }

    /**
     * 表达式为语句块时
     */
    public void testJexlEngine3() {
        //创建表达式引擎对象
        JexlEngine engine = new JexlEngine();
        //创建Context设值对象
        JexlContext context = new MapContext();
        ////表达式，表达式为逻辑语句
        String expressionStr = "if(a>b){c=a;}else{c=b};"; // 输出：2
        expressionStr = "while(a<b){a=a+b;}"; // 输出：3
        context.set("a", 1);
        context.set("b", 2);
        //使用引擎创建表达式对象
        Expression expression = engine.createExpression(expressionStr);
        //使用表达式对象开始计算表达式：
        Object evaluate = expression.evaluate(context);
        //输出：2
        System.out.println(evaluate);
    }
}
