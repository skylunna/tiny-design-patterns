package com.tiny.design.patterns.phase1.singleton;

/**
 * 懒汉式单例 - 线程安全版本（同步方法）
 * 优点：延迟加载，线程安全
 * 缺点：每次获取实例都需要同步，性能较差
 */
public class LazySingletonSafe {
    private static LazySingletonSafe instance;
    
    // 私有构造函数
    private LazySingletonSafe() {
        System.out.println("LazySingletonSafe created");
    }
    
    // 同步方法实现线程安全
    public static synchronized LazySingletonSafe getInstance() {
        if (instance == null) {
            instance = new LazySingletonSafe();
        }
        return instance;
    }
    
    public void doSomething() {
        System.out.println("LazySingletonSafe doing something...");
    }
}
