package design_pattern.singleton;

/**
 * @author zhanzhan
 * @date 2020/8/24 22:09
 *
 * 不仅可以解决线程同步，还可以防止反序列化
 */
public enum  Mgr08 {

    INSTANCE;

    public static void main(String[] args) {
        //模拟线程安全问题，启动100个线程
        for (int i = 0; i < 100; i++) {
            //同一个实例的哈希码相同
            new Thread(() -> System.out.println(Mgr08.INSTANCE.hashCode())).start();
        }
    }
}
