import org.junit.Test;

/**
 * @Description
 * @Author zhanzhan
 * @Date 2020/10/28 8:41
 * JVM指令集参考
 *  http://www.360doc.com/content/14/1013/17/9200790_416607533.shtml
 *  https://segmentfault.com/a/1190000008606277
 *
 * 程序编译的字节码  也可以cd到这个.class路径下运行命令javap -v -p Main
 * 两个重要的领域：局部变量表和操作数栈
 *
 * JVM指令集：        功能描述：
 *  0 aconst_null   将null推送至栈顶
 *  1 astore_1  将栈顶引用型数值存入第二个本地变量
 *  2 iconst_0  将int型常量值0推送至栈顶
 *  3 invokestatic #2 <java/lang/Boolean.valueOf>   调用静态方法 ， #2 方法的参数（实际上是对常量池的引用）
 *  6 astore_2  将栈顶引用型数值存入第三个本地变量
 *  7 getstatic #3 <java/lang/System.out>   获取指定类的静态域，并将其值压入栈顶
 * 10 aload_2   将第三个引用类型本地变量推送至栈顶
 * 11 invokevirtual #4 <java/lang/Boolean.booleanValue> 调用实列方法
 * 14 ifeq 21 (+7)
 * 17 iconst_0
 * 18 goto 25 (+7)  跳转指令
 * 21 aload_1   将第二个引用类型本地变量推送至栈顶。对于非静态方法（实列方法，静态方法当然无此），第一变量是this,即其对于的操作是aload_0.
 * 22 invokevirtual #5 <java/lang/Integer.intValue>
 * 25 invokevirtual #6 <java/io/PrintStream.println>
 * 28 return    当前方法返回void
 *
 */
public class Main {
    public static void main(String[] args) {
        Integer i = 11;
        Boolean flag = false;
        System.out.println(flag ? 0 : i);
    }

//    @Test
//    public void main() {
//        Integer i = null;
//        Boolean flag = false;
//        System.out.println(flag ? 0 : i);
//    }
}
