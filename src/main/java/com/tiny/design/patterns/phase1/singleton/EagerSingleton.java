package com.tiny.design.patterns.phase1.singleton;

/**
 * 饿汉式单例 - 线程安全，类加载时即初始化
 * 优点：实现简单，天然线程安全
 * 缺点：无论是否使用都会占用内存
 */
public class EagerSingleton {
    // 类加载时就创建实例
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    
    // 私有构造函数，防止外部实例化
    private EagerSingleton() {
        System.out.println("EagerSingleton created");
    }
    
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
    
    public void doSomething() {
        System.out.println("EagerSingleton doing something...");
    }
}
