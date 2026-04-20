package com.tiny.design.patterns.phase0.library;

import com.tiny.design.patterns.phase0.library.v2.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Copyright (c) 2026 skyluna. All Rights Reserved.
 *
 * @Author: skylunna
 * @Date: 2026/4/20 11:03
 * @Description: LibraryServiceTest
 *
 *      测试
 */
public class LibraryServiceTest {

    private final BookRepository repo = new MemoryBookRepository();
    private final LibraryService service = new LibraryService(repo);

    @Test
    void borrowPrintBook_success() {
        service.addBook(new PrintBook("Clean Code"));
        // 一次借阅纸质书 无异常
//        service.borrowByTitle("Clean Code");
        // 二次借阅 失败
//        assertThrows(IllegalStateException.class, () -> service.borrowByTitle("Clean Code"));

        // 第一次
        System.out.println("===== 第一次借阅 =====");
        service.borrowByTitle("Clean Code");
        System.out.println("✅ 第一次借阅成功");

        // 第二次（输出 + 断言）
        System.out.println("\n===== 第二次借阅 =====");
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            service.borrowByTitle("Clean Code");
        });

        System.out.println("❌ 第二次借阅失败，原因：" + exception.getMessage());
    }

    @Test
    void borrowEBook_alwaysAvailable() {
        service.addBook(new EBook("Effective Java", "https://example.com/dl/1"));
        // EBook 借阅不消耗库存，调用不抛异常即验证通过
        service.borrowByTitle("Effective Java");
        // 二次借阅依然成功（电子书特性）
        service.borrowByTitle("Effective Java");
    }
}