package com.tiny.design.patterns.phase0.library.v2;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (c) 2026 skyluna. All Rights Reserved.
 *
 * @Author: skylunna
 * @Date: 2026/4/20 10:40
 * @Description: MemoryBookRepository
 *
 *  内存
 */
public class MemoryBookRepository implements BookRepository {

    private final List<Borrowable> books = new ArrayList<>();

    @Override
    public List<Borrowable> findAll() {
        return List.copyOf(books);
    }

    @Override
    public void save(Borrowable book) {
        books.add(book);
    }

}
