package dev.ming.bookStore.service.impl;

import dev.ming.bookStore.exception.BasicException;
import dev.ming.bookStore.mapper.BookMapper;
import dev.ming.bookStore.mapper.BookOrderMapper;
import dev.ming.bookStore.mapper.BookRecordMapper;
import dev.ming.bookStore.mapper.EpisodeMapper;
import dev.ming.bookStore.model.entity.Book;
import dev.ming.bookStore.model.entity.BookOrder;
import dev.ming.bookStore.model.entity.BookRecord;
import dev.ming.bookStore.model.entity.Episode;
import dev.ming.bookStore.service.BookorderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class BookorderServiceImpl implements BookorderService {

    @Autowired
    private BookOrderMapper bookOrderMapper;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private EpisodeMapper episodeMapper;
    @Autowired
    private BookRecordMapper bookRecordMapper;

    @Override
    @Transactional
    public int orderBook(Integer userId,  Integer bookId) {

       //查詢是否已經購買過
        BookOrder bookOrder = bookOrderMapper.findByUserIdAndBookId(userId,bookId);

        //不存在才能下單
        if(null== bookOrder) {
            //下單紀錄
            Book book = bookMapper.findDetailById(bookId);
            BookOrder newBookOrder = new BookOrder();
            newBookOrder.setOutTradeNo(UUID.randomUUID().toString());
            newBookOrder.setState(1); //已付款
            newBookOrder.setCreateTime(new Date());
            newBookOrder.setTotalFee(book.getPrice());
            newBookOrder.setBookId(bookId);
            newBookOrder.setBookTitle(book.getTitle());
            newBookOrder.setBookImg(book.getImg());
            newBookOrder.setUserId(userId);

            bookOrderMapper.save(newBookOrder);

            //初始化閱讀紀錄
            Episode episode = episodeMapper.findFirstEpisodeByBookId(bookId);
            if(null==episode) throw new BasicException(404,"此書內容不存在，請聯繫客服人員");
            BookRecord newBookRecord = new BookRecord();
            newBookRecord.setUserId(userId);
            newBookRecord.setBookId(bookId);
            newBookRecord.setCurrentNum(episode.getNum());
            newBookRecord.setEpisodeId(episode.getId());
            newBookRecord.setCreateTime(new Date());

            bookRecordMapper.save(newBookRecord);

            return 1; //下單成功
        }
        return 0; //已經購買過
    }

    @Override
    public List<BookOrder> list(Integer userId) {

        List<BookOrder> bookOrderList = bookOrderMapper.list(userId);

        return bookOrderList;
    }
}
