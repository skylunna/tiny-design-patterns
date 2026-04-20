| 原则 | 核心思想 | V1 痛点 | V2 解法 |
| :--- | :--- | :--- | :--- |
| SRP | 一个类只做一件事 | Book 类同时管借阅、算罚金、导出PDF | 拆分为 Book、BorrowRecord、ReportExporter |
| OCP | 对扩展开放，对修改关闭 | `if(book.getType().equals("EBOOK"))` 分支泛滥 | 抽象 LibraryItem 接口 + 多态 |
| LSP | 子类可无缝替换父类 | Audiobook 重写 `print()` 抛 `UnsupportedOperationException` | 接口职责拆分，不强迫实现无关方法 |
| ISP | 接口尽量小而专 | ILibraryItem 包含 `borrow()`, `download()`, `print()` | 拆为 Borrowable, Downloadable |
| DIP | 依赖抽象而非具体实现 | LibraryService 直接 `new MySqlBookDao()` | 构造函数注入 BookRepository 接口 |


