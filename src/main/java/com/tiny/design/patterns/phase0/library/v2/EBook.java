package com.tiny.design.patterns.phase0.library.v2;

/**
 * Copyright (c) 2026 skyluna. All Rights Reserved.
 *
 * @Author: skylunna
 * @Date: 2026/4/20 10:30
 * @Description: EBook
 *      领域模型（SRP）
 *
 *      电子书
 */
public class EBook implements Borrowable, Downloadable {

    private final String title;
    private final String url;

    public EBook(String title, String url) {
        this.title = title;
        this.url = url;
    }

    @Override
    public void borrow() {
        System.out.println("授权访问" + title);
    }

    @Override
    public boolean isAvailable() {
        // 电子书一直可借 不会因为已借出而不能借
        return true;
    }

    @Override
    public String getDownloadUrl() {

        return url;
    }

    @Override
    public String getTitle() {
        return title;
    }
}