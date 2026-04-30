package com.tiny.design.patterns.phase1.singleton;

/**
 * 双重检查锁定（DCL）单例 - 推荐使用的线程安全懒汉式
 * 优点：延迟加载，线程安全，性能好（只在第一次创建时同步）
 * 注意：必须使用 volatile 关键字防止指令重排序
 */
public class DoubleCheckedLockingSingleton {
    // volatile 保证可见性和禁止指令重排序
    private static volatile DoubleCheckedLockingSingleton instance;
    
    // 私有构造函数
    private DoubleCheckedLockingSingleton() {
        System.out.println("DoubleCheckedLockingSingleton created");
    }
    
    public static DoubleCheckedLockingSingleton getInstance() {
        // 第一次检查：避免不必要的同步
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                // 第二次检查：确保只有一个实例被创建
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
    
    public void doSomething() {
        System.out.println("DoubleCheckedLockingSingleton doing something...");
    }
}
