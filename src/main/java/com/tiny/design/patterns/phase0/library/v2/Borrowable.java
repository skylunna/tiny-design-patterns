package com.tiny.design.patterns.phase0.library.v2;

/**
 * Copyright (c) 2026 skyluna. All Rights Reserved.
 *
 * @Author: skylunna
 * @Date: 2026/4/20 10:24
 * @Description: Borrowable
 *      接口拆分（ISP + OCP + LSP）
 *      借书
 */
public interface Borrowable {

    void borrow();

    boolean isAvailable();

    String getTitle();
}