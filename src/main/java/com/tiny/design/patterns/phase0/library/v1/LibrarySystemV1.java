package com.tiny.design.patterns.phase0.library.v1;

import java.util.*;

/**
 * Copyright (c) 2026 skyluna. All Rights Reserved.
 *
 * @Author:  skylunna
 * @Date: 2026/4/20 10:05
 * @Description: LibrarySystemV1
 *      反面教材
 *      严重违背 SRP、OCP、ISP、DIP
 *      Book 类同时管借阅、算罚金、导出PDF
 *
 *      痛点:
 *          新增 VRBOOK 类型？改3处。
 *          要换数据库？改 LibrarySystemV1。
 *          要换报表格式？改 LibrarySystemV1
 *
 *      详情: notes.md
 */
public class LibrarySystemV1 {
    private final List<Map<String, Object>> books = new ArrayList<>();

    public void addBook(String title, String type, String author) {
        Map<String, Object> book = new HashMap<>();
        book.put("title", title);
        book.put("type", type);
        book.put("author", author);
        books.add(book);
    }

    public void borrowBook(String title) {
        for (Map<String, Object> b : books) {
            if (b.get("title").equals(title)) {
                String type = (String) b.get("type");
                if ("PRINT".equals(type)) {
                    System.out.println("纸质书已登记出库");
                } else if ("EBOOK".equals(type)) {
                    System.out.println("电子书下载连接已发送");
                } else if ("AUDIO".equals(type)) {
                    System.out.println("音频流已授权");
                } else {
                    throw new IllegalArgumentException("未知类型");
                }
                return;
            }
        }
        throw new NoSuchElementException("书籍不存在");
    }

    public void generateReport() {
        // 混杂了数据查询、格式化、输出逻辑
        System.out.println("--- 报表 ---");
        for (Map<String, Object> b : books) {
            System.out.println(b.get("title") + " [" + b.get("type") + "]");
        }
    }
}