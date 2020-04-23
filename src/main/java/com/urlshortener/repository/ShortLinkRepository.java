package com.urlshortener.repository;

import com.urlshortener.model.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortLinkRepository extends JpaRepository<ShortLink,Long> {

    ShortLink findByKeyword(@Param("keyword")String keyword);

    ShortLink findByLongLink(@Param("longLink")String longLink);
}
