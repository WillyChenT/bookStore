package dev.ming.bookStore.controller;

import dev.ming.bookStore.model.entity.Book;
import dev.ming.bookStore.model.entity.BookOrder;
import dev.ming.bookStore.model.request.OrderRequest;
import dev.ming.bookStore.service.BookorderService;
import dev.ming.bookStore.utils.JsonData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("api/v1/pri/order")
public class BookOrderController {

    @Autowired
    private BookorderService bookOrderService;

    @PostMapping("orderBook")
    public JsonData orderBook(@RequestBody OrderRequest orderRequest, HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");

        int rows = bookOrderService.orderBook(userId,orderRequest.getBookId());

        if(1==rows){
            return JsonData.buildSuccess("下單成功");
        }else if(0==rows){
            return JsonData.buildError("已經購買過");
        }else {
            return JsonData.buildError("下單異常");
        }
    }

    @GetMapping("list")
    public JsonData list(HttpServletRequest request){

        Integer userId = (Integer) request.getAttribute("user_id");

        List<BookOrder> list = bookOrderService.list(userId);

        if(null!=list && list.size()>0){
            return JsonData.buildSuccess(list);
        }else if(null!=list && list.size()<=0){
            return JsonData.buildError("尚未購買");
        }else{
            return JsonData.buildError("查詢購買清單異常");
        }

    }
}
