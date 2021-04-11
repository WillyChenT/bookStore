package dev.ming.bookStore.service;

import dev.ming.bookStore.model.entity.Book;
import dev.ming.bookStore.model.entity.BookOrder;

import java.util.List;

public interface BookorderService {

    int orderBook(Integer userId, Integer bookId);

    List<BookOrder> list(Integer userId);
}
