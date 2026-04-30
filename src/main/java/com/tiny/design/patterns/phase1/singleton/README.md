# 单例模式 (Singleton Pattern)

## 概述

单例模式是一种创建型设计模式，它确保一个类只有一个实例，并提供一个全局访问点来获取该实例。

## 适用场景

1. **需要频繁创建和销毁的对象**
2. **创建对象时消耗过多资源或时间**
3. **对象需要被共享访问**
4. **需要统一控制资源的场景**（如数据库连接池、线程池、日志对象等）

## 实现方式

### 1. 饿汉式单例 (Eager Singleton)

```java
public class EagerSingleton {
    private static final EagerSingleton INSTANCE = new EagerSingleton();
    
    private EagerSingleton() {}
    
    public static EagerSingleton getInstance() {
        return INSTANCE;
    }
}
```

**优点：**
- 实现简单
- 天然线程安全（类加载时初始化）

**缺点：**
- 无论是否使用都会占用内存
- 不支持延迟加载

### 2. 懒汉式单例 - 线程不安全版本

```java
public class LazySingletonUnsafe {
    private static LazySingletonUnsafe instance;
    
    private LazySingletonUnsafe() {}
    
    public static LazySingletonUnsafe getInstance() {
        if (instance == null) {
            instance = new LazySingletonUnsafe();
        }
        return instance;
    }
}
```

**优点：**
- 延迟加载，节省内存

**缺点：**
- 多线程环境下可能创建多个实例（线程不安全）

### 3. 懒汉式单例 - 同步方法版本

```java
public class LazySingletonSafe {
    private static LazySingletonSafe instance;
    
    private LazySingletonSafe() {}
    
    public static synchronized LazySingletonSafe getInstance() {
        if (instance == null) {
            instance = new LazySingletonSafe();
        }
        return instance;
    }
}
```

**优点：**
- 延迟加载
- 线程安全

**缺点：**
- 每次获取实例都需要同步，性能较差

### 4. 双重检查锁定 (Double-Checked Locking, DCL) ⭐推荐

```java
public class DoubleCheckedLockingSingleton {
    private static volatile DoubleCheckedLockingSingleton instance;
    
    private DoubleCheckedLockingSingleton() {}
    
    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }
}
```

**优点：**
- 延迟加载
- 线程安全
- 性能好（只在第一次创建时同步）

**注意：**
- 必须使用 `volatile` 关键字防止指令重排序

### 5. 静态内部类单例 ⭐推荐

```java
public class StaticInnerClassSingleton {
    private StaticInnerClassSingleton() {}
    
    private static class SingletonHolder {
        private static final StaticInnerClassSingleton INSTANCE = new StaticInnerClassSingleton();
    }
    
    public static StaticInnerClassSingleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}
```

**优点：**
- 延迟加载
- 线程安全
- 性能好
- 代码简洁

**原理：**
- 利用 JVM 的类加载机制保证线程安全
- 只有在调用 `getInstance()` 时才会加载内部类

### 6. 枚举单例 ⭐⭐最推荐

```java
public enum EnumSingleton {
    INSTANCE;
    
    public void doSomething() {
        // 业务方法
    }
}
```

**优点：**
- 代码最简洁
- 天然线程安全
- 防止反射攻击
- 防止序列化破坏

**缺点：**
- 无法延迟加载（枚举在类加载时就初始化）

## 各种实现方式对比

| 实现方式 | 延迟加载 | 线程安全 | 性能 | 防止反射 | 防止序列化 | 推荐度 |
|---------|---------|---------|------|---------|-----------|--------|
| 饿汉式 | ❌ | ✅ | 高 | ❌ | ❌ | ⭐⭐ |
| 懒汉式（不安全） | ✅ | ❌ | 高 | ❌ | ❌ | ❌ |
| 懒汉式（同步） | ✅ | ✅ | 低 | ❌ | ❌ | ⭐ |
| DCL | ✅ | ✅ | 高 | ❌ | ❌ | ⭐⭐⭐ |
| 静态内部类 | ✅ | ✅ | 高 | ❌ | ❌ | ⭐⭐⭐ |
| 枚举 | ❌ | ✅ | 高 | ✅ | ✅ | ⭐⭐⭐⭐⭐ |

## 单例模式的破坏与防护

### 1. 反射攻击

普通单例可以通过反射破坏：

```java
Constructor<EagerSingleton> constructor = EagerSingleton.class.getDeclaredConstructor();
constructor.setAccessible(true);
EagerSingleton newInstance = constructor.newInstance(); // 创建了第二个实例！
```

**防护方法：**
- 在构造函数中添加检查：`if (INSTANCE != null) throw new RuntimeException();`
- 使用枚举单例（天然防止反射攻击）

### 2. 序列化破坏

序列化和反序列化会创建新的实例：

```java
// 序列化
ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("singleton.obj"));
oos.writeObject(instance);

// 反序列化
ObjectInputStream ois = new ObjectInputStream(new FileInputStream("singleton.obj"));
Singleton deserialized = (Singleton) ois.readObject(); // 创建了第二个实例！
```

**防护方法：**
- 实现 `readResolve()` 方法返回已有实例
- 使用枚举单例（天然防止序列化破坏）

## 最佳实践

1. **优先使用枚举单例** - 最安全、最简洁
2. **如果需要延迟加载** - 使用静态内部类或 DCL
3. **避免使用懒汉式（不安全版本）**
4. **考虑使用依赖注入框架**（如 Spring）管理单例

## Spring 中的单例

Spring 容器默认以单例模式管理 Bean：

```java
@Service
public class MyService {
    // Spring 默认以单例模式管理
}
```

Spring 的单例是容器级别的单例，与单例模式的单例略有不同。

## 测试运行

```bash
mvn test -Dtest=com.tiny.design.patterns.phase1.singleton.SingletonTest
```

## 参考资料

- 《设计模式：可复用面向对象软件的基础》
- 《Effective Java》第三版 - Item 3: Enforce the singleton property with a private constructor or an enum type
