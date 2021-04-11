package dev.ming.bookStore.service;

import dev.ming.bookStore.model.entity.Book;
import dev.ming.bookStore.model.entity.PromoBanner;

import java.util.List;

public interface BookService {

    List<PromoBanner> listBanner();

    List<Book> listBook();

    Book findDetailById(Integer videoId);
}
