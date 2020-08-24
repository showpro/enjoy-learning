package design_pattern.singleton;

/**
 * @author zhanzhan
 * @date 2020/8/24 22:09
 *
 * 静态内部类方式
 * JVM保证单列
 * 加载外部类时不会加载内部类，只有在调用的时候才会加载内部类，这样可以实现懒加载
 */
public class Mgr07 {

    /**
     * 构造方法私有；出了这个类，new不出来
     */
    private Mgr07() {};


    /**
     * 提供一个获取实例的方法
     *
     * static synchronized 锁定的是Mgr04.class对象
     * @return
     */
    private static class Mgr07Holder {
        private final static Mgr07 INSTANCE = new Mgr07();
    }
    private static Mgr07 getInstance() {
        return Mgr07Holder.INSTANCE;
    }

    public void method() {
        System.out.println("m");
    }

    public static void main(String[] args) {
        //模拟线程安全问题，启动100个线程
        for (int i = 0; i < 100; i++) {
            //同一个实例的哈希码相同
            new Thread(() -> System.out.println(Mgr07.getInstance()
                .hashCode())).start();
        }
    }
}
