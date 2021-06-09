/**
 * @Description 每个线程都拥有了ThreadLocal，也就是说每个线程都拥有了自己独立的一个变量
 * @Author zhanzhan
 * @Date 2021/5/26 9:13
 */
public class ThreadLocalDemo {
    /**
     * ThreadLocal变量，每个线程都有一个副本，互不干扰
     * <p>
     * 其中 static 是为了确保全局只有一个保存 String 对象的 ThreadLocal 实例；
     * <p>
     * final 确保 ThreadLocal 的实例不可更改，防止被意外改变，导致放入的值和取出来的不一致，另外还能防止 ThreadLocal 的内存泄漏。
     */
    public static final ThreadLocal<String> THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 主线程
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new ThreadLocalDemo().threadLocalTest();
    }

    public void threadLocalTest() throws Exception {
        // 主线程设置值
        THREAD_LOCAL.set("hubei");
        String value = THREAD_LOCAL.get();
        System.out.println("Thread-0线程执行之前，" + Thread.currentThread().getName() + "线程取到的值：" + value);

        // 创建一个新的线程 Thread-0
        new Thread(new Runnable() {
            @Override
            public void run() {
                String value = THREAD_LOCAL.get();
                System.out.println(Thread.currentThread().getName() + "线程取到的值：" + value);
                // 设置 threadLocal
                THREAD_LOCAL.set("hangzhou");
                value = THREAD_LOCAL.get();
                System.out.println("重新设置之后，" + Thread.currentThread().getName() + "线程取到的值为：" + value);
                System.out.println(Thread.currentThread().getName() + "线程执行结束");
            }
        }).start();

        // 创建一个新的线程 Thread-1
        new Thread(() -> {
            String v = THREAD_LOCAL.get();
            System.out.println(Thread.currentThread().getName() + "线程取到的值：" + v);
            // 设置 threadLocal
            THREAD_LOCAL.set("wuhan");
            v = THREAD_LOCAL.get();
            System.out.println("重新设置之后，" + Thread.currentThread().getName() + "线程取到的值为：" + v);
            System.out.println(Thread.currentThread().getName() + "线程执行结束");
        }).start();

        // 等待所有线程执行结束
        Thread.sleep(3000L);
        value = THREAD_LOCAL.get();
        System.out.println("Thread-0线程执行之后，" + Thread.currentThread().getName() + "线程取到的值：" + value);

        /**
         * 执行结果：
         * Thread-0线程执行之前，main线程取到的值：hubei
         * Thread-0线程取到的值：null
         * 重新设置之后，Thread-0线程取到的值为：hangzhou
         * Thread-0线程执行结束
         * Thread-1线程取到的值：null
         * 重新设置之后，Thread-1线程取到的值为：wuhan
         * Thread-1线程执行结束
         * Thread-0线程执行之后，main线程取到的值：hubei
         */
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }
}

