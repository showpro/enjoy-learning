package design_pattern.singleton;

/**
 * @author zhanzhan
 * @date 2020/8/24 22:09
 *
 * lazy loading
 * 也称懒汉式
 * 虽然达到了按需初始化的目的，但却带来了线程不安全的问题
 * 如何解决呢？可以通过加锁synchronized解决，但也带来效率下降
 */
public class Mgr06 {
    // 定义一个静态实例
    private static volatile Mgr06 INSTANCE; //JIT 防止指令重排

    /**
     * 构造方法私有；出了这个类，new不出来
     */
    private Mgr06() {};

    /**
     * 提供一个获取实例的方法
     *
     * static synchronized 锁定的是Mgr04.class对象
     * @return
     */
    public static Mgr06 getInstance() {
        if (null == INSTANCE) {//这一步有线程安全问题
            //双重检查
            synchronized (Mgr06.class) {
                if (null == INSTANCE) {
                    //睡眠1毫秒,使线程安全问题更容易展现出来
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    INSTANCE = new Mgr06();
                }
            }
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
            new Thread(() -> System.out.println(Mgr06.getInstance()
                .hashCode())).start();
        }
    }
}
