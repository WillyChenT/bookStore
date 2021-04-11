package dev.ming.bookStore.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderRequest {

    @JsonProperty("book_id")
    private Integer bookId;

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }
}
