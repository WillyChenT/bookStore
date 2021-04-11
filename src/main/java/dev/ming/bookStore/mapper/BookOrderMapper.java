package dev.ming.bookStore.mapper;

import dev.ming.bookStore.model.entity.BookOrder;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookOrderMapper {

    BookOrder findByUserIdAndBookId(@Param("user_id") Integer userId, @Param("book_id") Integer bookId);

    int save(BookOrder bookOrder);

    List<BookOrder> list(Integer userId);
}
