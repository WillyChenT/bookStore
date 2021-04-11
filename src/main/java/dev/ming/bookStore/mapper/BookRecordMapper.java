package dev.ming.bookStore.mapper;

import dev.ming.bookStore.model.entity.BookRecord;

public interface BookRecordMapper {

    int save(BookRecord newBookRecord);
}
