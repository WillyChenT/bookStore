package dev.ming.bookStore.service.impl;

import dev.ming.bookStore.mapper.BookMapper;
import dev.ming.bookStore.model.entity.Book;
import dev.ming.bookStore.model.entity.PromoBanner;
import dev.ming.bookStore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    /**
     * 查詢輪播圖
     * @return
     */
    @Override
    public List<PromoBanner> listBanner() {

        List<PromoBanner> bannerList = bookMapper.listPromoBanner();
        return bannerList;
    }

    /**
     * 查書籍列表
     * @return
     */
    @Override
    public List<Book> listBook() {
        return bookMapper.listBook();
    }

    @Override
    public Book findDetailById(Integer bookId) {

        Book book = bookMapper.findDetailById(bookId);

        return book;
    }


}
