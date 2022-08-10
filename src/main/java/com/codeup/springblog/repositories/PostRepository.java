package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Transactional
    @Modifying
    @Query("update Post post set post.title = :title, post.body = :body  where post.id = :id")
    void updatePost(@Param("id") Long id, @Param("title") String title, @Param("body") String body);

    Post findByTitle(String title);
}
