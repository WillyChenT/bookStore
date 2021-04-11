package dev.ming.bookStore.mapper;

import dev.ming.bookStore.model.entity.Episode;
import org.apache.ibatis.annotations.Param;

public interface EpisodeMapper {

    Episode findFirstEpisodeByBookId(@Param("book_id") Integer bookId);
}
