package com.emyblogp4.Service;

import com.emyblogp4.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(long postId, CommentDto commentDto);

    void deleteComment(long postId, long commentId);
    List<CommentDto> getPostComments(long postId);

    CommentDto updateComment(long commentId, CommentDto commentDto);
}
