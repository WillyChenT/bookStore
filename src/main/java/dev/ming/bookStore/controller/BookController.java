package dev.ming.bookStore.controller;

import dev.ming.bookStore.model.entity.Book;
import dev.ming.bookStore.model.entity.PromoBanner;
import dev.ming.bookStore.service.BookService;
import dev.ming.bookStore.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/pub/book")
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * 查詢輪播圖
     * @return
     */
    @GetMapping("list_banner")
    public JsonData listBanner(){
        List<PromoBanner> bannerList = bookService.listBanner();

        return JsonData.buildSuccess(bannerList);
    }

    /**
     * 查書籍列表
     */
    @GetMapping("list_book")
    public JsonData listBook(){
        List<Book> bookList = bookService.listBook();

        return JsonData.buildSuccess(bookList);
    }

    /**
     * 查詢影片資訊(包含章節、集數)
     */
    @GetMapping("find_detail_by_id")
    public JsonData findDetailById(@RequestParam(value="book_id",required = true) Integer bookId){
        Book book = bookService.findDetailById(bookId);

        return JsonData.buildSuccess(book);
    }
}
