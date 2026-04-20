package com.tiny.design.patterns.phase0.library.v2;

import java.util.List;

/**
 * Copyright (c) 2026 skyluna. All Rights Reserved.
 *
 * @Author: skylunna
 * @Date: 2026/4/20 10:34
 * @Description: BookRepository
 *      仓储接口与依赖倒置（DIP）
 *
 *      书仓库
 */
public interface BookRepository {

    List<Borrowable> findAll();

    void save(Borrowable book);
}
