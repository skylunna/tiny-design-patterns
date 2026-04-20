package com.tiny.design.patterns.phase0.library.v2;

import java.util.NoSuchElementException;

/**
 * Copyright (c) 2026 skyluna. All Rights Reserved.
 *
 * @Author: skylunna
 * @Date: 2026/4/20 10:54
 * @Description: LibraryService
 *
 *      服务层（OCP + DIP 落地）
 */
public class LibraryService {
    private final BookRepository repository;

    public LibraryService(BookRepository bookRepository) {
        this.repository = bookRepository;
    }

    public void borrowByTitle(String title) {
        Borrowable book  = repository.findAll().stream()
                .filter(b -> b.getTitle().equals(title))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("书籍不存在" + title));
        book.borrow();
    }

    public void addBook(Borrowable book) {
        repository.save(book);
    }
}