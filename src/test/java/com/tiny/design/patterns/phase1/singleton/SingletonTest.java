package com.tiny.design.patterns.phase1.singleton;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 单例模式测试类
 */
public class SingletonTest {

    @Test
    public void testEagerSingleton() {
        EagerSingleton instance1 = EagerSingleton.getInstance();
        EagerSingleton instance2 = EagerSingleton.getInstance();
        
        // 验证是同一个实例
        assertSame(instance1, instance2);
        assertNotNull(instance1);
        
        // 测试功能
        instance1.doSomething();
    }

    @Test
    public void testLazySingletonSafe() {
        LazySingletonSafe instance1 = LazySingletonSafe.getInstance();
        LazySingletonSafe instance2 = LazySingletonSafe.getInstance();
        
        // 验证是同一个实例
        assertSame(instance1, instance2);
        assertNotNull(instance1);
        
        instance1.doSomething();
    }

    @Test
    public void testDoubleCheckedLockingSingleton() {
        DoubleCheckedLockingSingleton instance1 = DoubleCheckedLockingSingleton.getInstance();
        DoubleCheckedLockingSingleton instance2 = DoubleCheckedLockingSingleton.getInstance();
        
        // 验证是同一个实例
        assertSame(instance1, instance2);
        assertNotNull(instance1);
        
        instance1.doSomething();
    }

    @Test
    public void testStaticInnerClassSingleton() {
        StaticInnerClassSingleton instance1 = StaticInnerClassSingleton.getInstance();
        StaticInnerClassSingleton instance2 = StaticInnerClassSingleton.getInstance();
        
        // 验证是同一个实例
        assertSame(instance1, instance2);
        assertNotNull(instance1);
        
        instance1.doSomething();
    }

    @Test
    public void testEnumSingleton() {
        EnumSingleton instance1 = EnumSingleton.INSTANCE;
        EnumSingleton instance2 = EnumSingleton.INSTANCE;
        
        // 验证是同一个实例
        assertSame(instance1, instance2);
        assertNotNull(instance1);
        
        instance1.doSomething();
    }

    @Test
    public void testMultithreadedSingleton() throws InterruptedException {
        // 测试多线程环境下的单例安全性
        final DoubleCheckedLockingSingleton[] instances = new DoubleCheckedLockingSingleton[10];
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                instances[index] = DoubleCheckedLockingSingleton.getInstance();
            });
        }

        // 启动所有线程
        for (Thread thread : threads) {
            thread.start();
        }

        // 等待所有线程完成
        for (Thread thread : threads) {
            thread.join();
        }

        // 验证所有线程获取的都是同一个实例
        for (int i = 1; i < 10; i++) {
            assertSame(instances[0], instances[i], "多线程环境下应该返回同一个实例");
        }
    }

    @Test
    public void testPrivateConstructor() throws Exception {
        // 测试防止反射攻击（仅对非枚举单例有效）
        // 注意：普通的私有构造函数可以通过反射破坏，需要额外处理
        // 这里演示的是普通实现会被反射破坏，实际生产中需要在构造函数中增加检查
        var constructor = EagerSingleton.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        
        // 对于普通单例，反射确实可以创建新实例
        // 这是一个已知限制，如果需要完全防止反射，需要在构造函数中添加检查
        // 例如：if (INSTANCE != null) throw new RuntimeException();
        
        // 这个测试展示了普通单例的局限性
        // 枚举单例是防止反射攻击的最佳选择
        Object newInstance = constructor.newInstance();
        assertNotNull(newInstance);
        System.out.println("注意：普通单例可以通过反射创建新实例，这是其局限性");
        System.out.println("推荐使用枚举单例来防止反射攻击");
    }
}
