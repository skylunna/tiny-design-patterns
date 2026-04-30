package com.tiny.design.patterns.phase1.singleton;

/**
 * 静态内部类单例 - 推荐的线程安全懒汉式实现
 * 优点：延迟加载，线程安全，性能好，代码简洁
 * 原理：利用 JVM 的类加载机制保证线程安全
 */
public class StaticInnerClassSingleton {
    
    // 私有构造函数
    private StaticInnerClassSingleton() {
        System.out.println("StaticInnerClassSingleton created");
    }
    
    // 静态内部类，只有在调用 getInstance() 时才会被加载
    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
    
    public static StaticInnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
    
    public void doSomething() {
        System.out.println("StaticInnerClassSingleton doing something...");
    }
}
