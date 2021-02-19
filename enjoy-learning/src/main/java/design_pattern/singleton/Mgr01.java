package design_pattern.singleton;

/**
 * @author zhanzhan
 * @date 2020/8/24 22:09
 *
 * 饿汉式
 * 类加载到内存后，就实例化一个单列，JVM保证线程安全
 * 简单实用，推荐使用！
 * 唯一缺点：不管用到与否，类装载时就完成实例化
 * （话说你不用的，你装载它干啥）
 */
public class Mgr01 {
    // 定义一个静态实例
    private static final Mgr01 INSTANCE = new Mgr01();

    /**
     * 构造方法私有；不能由外部调用的(出了这个类，new不出来)
     */
    private Mgr01() {};

    /**
     * 提供一个获取实例的方法
     * @return
     */
    public static Mgr01 getInstance() {
        return INSTANCE;
    }

    public void method() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr01 m1 = Mgr01.getInstance();
        Mgr01 m2 = Mgr01.getInstance();
        System.out.println(m1 == m2);
    }
}
