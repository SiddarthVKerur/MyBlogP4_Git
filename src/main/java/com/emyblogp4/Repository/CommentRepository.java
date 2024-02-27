package com.emyblogp4.Repository;

import com.emyblogp4.Entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> getCommentsByPostId(long postId);
}
