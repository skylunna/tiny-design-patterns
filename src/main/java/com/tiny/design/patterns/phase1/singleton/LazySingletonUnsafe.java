package com.tiny.design.patterns.phase1.singleton;

/**
 * 懒汉式单例 - 线程不安全版本
 * 优点：延迟加载，节省内存
 * 缺点：多线程环境下可能创建多个实例
 */
public class LazySingletonUnsafe {
    private static LazySingletonUnsafe instance;
    
    // 私有构造函数
    private LazySingletonUnsafe() {
        System.out.println("LazySingletonUnsafe created");
    }
    
    // 线程不安全的 getInstance 方法
    public static LazySingletonUnsafe getInstance() {
        if (instance == null) {
            instance = new LazySingletonUnsafe();
        }
        return instance;
    }
    
    public void doSomething() {
        System.out.println("LazySingletonUnsafe doing something...");
    }
}
