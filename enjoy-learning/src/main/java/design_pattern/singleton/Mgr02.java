package design_pattern.singleton;

/**
 * @author zhanzhan
 * @date 2020/8/24 22:09
 *
 * 跟01是一个意思
 */
public class Mgr02 {
    // 定义一个静态实例
    private static final Mgr02 INSTANCE ;
    static {
        INSTANCE = new Mgr02();
    }

    /**
     * 构造方法私有；出了这个类，new不出来
     */
    private Mgr02() {};

    /**
     * 提供一个获取实例的方法
     * @return
     */
    public static Mgr02 getInstance() {
        return INSTANCE;
    }

    public void method() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        Mgr02 m1 = Mgr02.getInstance();
        Mgr02 m2 = Mgr02.getInstance();
        System.out.println(m1 == m2);
    }
}
