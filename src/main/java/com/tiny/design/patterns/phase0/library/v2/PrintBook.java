package com.tiny.design.patterns.phase0.library.v2;

/**
 * Copyright (c) 2026 skyluna. All Rights Reserved.
 *
 * @Author: skylunna
 * @Date: 2026/4/20 10:26
 * @Description: PrintBook
 *      领域模型（SRP）
 *
 *      纸质书
 */
public class PrintBook implements Borrowable {

    private final String title;

    private boolean available = true;

    public PrintBook(String title) {
        this.title = title;
    }

    @Override
    public void borrow() {
        if (available) {
            available = false;
        } else {
            throw new IllegalStateException("已借出");
        }
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public String getTitle() {
        return title;
    }
}