package com.tiny.design.patterns.phase1.singleton;

/**
 * 枚举单例 - 最简洁、最安全的单例实现
 * 优点：代码简洁，天然线程安全，防止反射攻击和序列化破坏
 * 缺点：无法延迟加载（枚举在类加载时就初始化）
 */
public enum EnumSingleton {
    INSTANCE;
    
    public void doSomething() {
        System.out.println("EnumSingleton doing something...");
    }
}
