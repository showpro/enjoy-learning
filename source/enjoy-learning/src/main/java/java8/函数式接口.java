package java8;

/**
 * @Description
 * @Author zhanzhan
 * @Date 2020/10/30 10:56
 * <p>
 * 一、函数式接口
 * 函数式接口（functional interface 也叫功能性接口，其实是同一个东西）。简单来说，函数式接口就是一个有且仅有一个抽象方法,但是可以有多个非抽象方法的接口。比如Java标准库中的java.lang.Runnable和 java.util.Comparator都是典型的函数式接口。
 * java 8提供 @FunctionalInterface作为注解,这个注解是非必须的，只要接口符合函数式接口的标准（即只包含一个方法的接口），虚拟机会自动判断， 但 最好在接口上使用注解@FunctionalInterface进行声明，以免团队的其他人员错误地往接口中添加新的方法。
 * <p>
 * Java中的lambda无法单独出现，它需要一个函数式接口来盛放，lambda表达式方法体其实就是函数接口的实现.
 * <p>
 * 二、lambda语法
 * 包含三部分：
 * 1、一个括号内用逗号分隔的形式参数，参数是函数式接口里面方法的参数
 * 2、一个箭头符号：->
 * 3、方法体，可以是表达式和代码块。
 * <p>
 * 三、方法引用
 * 其实是lambda表达式的一种简化写法。所引用的方法其实是lambda表达式的方法体实现，语法也很简单，左边是容器（可以是类名，实例名），中间是"::"，右边是相应的方法名。如下所示：
 * <p>
 * ObjectReference::methodName
 * 一般方法的引用格式:
 * 1、如果是静态方法，则是ClassName::methodName。如 Object ::equals
 * 2、如果是实例方法，则是Instance::methodName。如 Object obj=new Object();obj::equals;
 * 3、构造函数.则是ClassName::new
 */
public class 函数式接口 {
    public static void main(String[] args) {

        runThreadByLambda();

        runThreadByInnerClass();

        /*
         * 方法引用
         */
        函数式接口 函数式接口m = new 函数式接口();
        new Thread(函数式接口m::r).start();
        Runnable r1 = 函数式接口::r1;//lambda无法单独出现，它需要一个函数式接口来盛放
        new Thread(r1).start();
    }

    public static void runThreadByLambda() {
		/*
		 Runnable就是一个函数式接口：他只有一个方法run()方法。
		 1、因为run()方法没有参数，所以   ->前面的()中不需要声明形参
		 2、run返回的是void，所以不需要return。
		 3、->后面写的代码其实就是定义在run方法内的代码。因为此处代码只有一行，所以{}也可以省略。如果此处多与一行，则无法省略。
		 */
        Runnable runnable = () -> System.out.println("这个是用lambda实现的线程。。。");
        new Thread(runnable).start();
    }

    public static void runThreadByInnerClass() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                System.out.println("这个是用内部类实现的线程。。。");

            }
        };
        new Thread(runnable).start();
    }

    public void r() {
        System.out.println("方法引用的代码...");
    }

    public static void r1() {
        System.out.println("方法引用的代码1...");
    }
}
