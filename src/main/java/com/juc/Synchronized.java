package com.juc;

/**
 * JVM(JDK 1.6+) 中锁的优化
 * <p>
 * javac src/main/java/com/juc/Synchronized.java
 * javap -verbose src/main/java/com/juc/Synchronized.class
 * <p>
 * -XX:+UnlockDiagnosticVMOptions               解锁诊断参数
 * -XX:-DoEscapeAnalysis                        关闭逃逸分析(JDK8 默认开启)，开启逃逸分析后，编译器可以对代码作如下优化
 * _                                            1. 锁消除
 * _                                            2. 栈上分配
 * _                                            3. 分离对象或标量替换
 * -XX:+PrintEscapeAnalysis                     输出逃逸分析，需使用 Debug JDK (Not a product JDK) 并解锁诊断参数
 * -XX:-EliminateLocks                          关闭锁消除(JDK8 默认开启)
 * -XX:-EliminateAllocations                    关闭标量替换(JDK8 默认开启)
 * -XX:+PrintEliminateAllocations               输出标量替换信息，需使用 Debug JDK (Not a product JDK) 并解锁诊断参数
 *
 * @author zhangqiang
 * @since 2021/4/7 11:37 下午
 */
public class Synchronized {

    /*

    # 使用 Debug-OpenJDK 执行，输出诊断信息
    /Users/zhangqiang/openjdk-11-debug-binary/bin/java \
        -server \
        -XX:+UnlockDiagnosticVMOptions \
        -XX:+PrintEscapeAnalysis \
        -classpath /Users/zhangqiang/IdeaProjects/leetcode/target/classes \
        com.juc.Synchronized

    */
    public static void main(String[] args) throws InterruptedException {
        Synchronized sy = new Synchronized();
        // sy.lockCoarsening();
        sy.lockElimination();
    }


    /**
     * 锁粗化
     * <p>
     * 减少不必要的紧连在一起的 unlock，lock 操作，将多个连续的锁扩展成一个范围更大的锁
     * <p>
     *
     * @return
     */
    public void lockCoarsening() {
        synchronized (this) {
            System.out.println("1");
        }
        synchronized (this) {
            System.out.println("2");
        }
        synchronized (this) {
            System.out.println("3");
        }
        // -------- 锁粗化后会变成下面的 --------
        synchronized (this) {
            System.out.println("1");
            System.out.println("2");
            System.out.println("3");
        }
    }

    /**
     * 锁消除：锁消除是建立在逃逸分析的基础之上的优化，经过逃逸分析发现锁不会有任何竞争
     * <p>
     */
    public void lockElimination() throws InterruptedException {
        // 为了让 JIT 生效，进行逃逸分析，需要代码运行足够多次
        for (int i = 0; i < 10000000; i++) {
            StringBuffer sb = new StringBuffer();
            // StringBuffer.append() 方法是加了 synchronized 的同步操作
            sb.append("1");
            sb.append("2");
        }
    }

}
