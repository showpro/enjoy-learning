package design_pattern.singleton;

/**
 * @author zhanzhan
 * @date 2020/8/24 22:09
 *
 * lazy loading
 * 也称懒汉式
 * 虽然达到了按需初始化的目的，但却带来了线程不安全的问题
 * 如何解决呢？
 */
public class Mgr03 {
    // 定义一个静态实例
    private static Mgr03 INSTANCE;

    /**
     * 构造方法私有；出了这个类，new不出来
     */
    private Mgr03() {}

    ;

    /**
     * 提供一个获取实例的方法
     *
     * @return
     */
    public static Mgr03 getInstance() {
        if (null == INSTANCE) {//这一步有线程安全问题

            //睡眠1毫秒,使线程安全问题更容易展现出来
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            INSTANCE = new Mgr03();
        }
        return INSTANCE;
    }

    public void method() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        //模拟线程安全问题，启动100个线程
        for (int i = 0; i < 100; i++) {
            //同一个实例的哈希码相同
            new Thread(() -> System.out.println(Mgr03.getInstance()
                .hashCode())).start();
        }
    }
}
