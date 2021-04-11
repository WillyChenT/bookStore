package dev.ming.bookStore.mapper;

import dev.ming.bookStore.model.entity.Book;
import dev.ming.bookStore.model.entity.PromoBanner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper {

    List<PromoBanner> listPromoBanner();

    List<Book> listBook();


    Book findDetailById(@Param("book_id") int bookId);
}
