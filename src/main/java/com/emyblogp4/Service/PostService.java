package com.emyblogp4.Service;

import com.emyblogp4.payload.PostDto;
import com.emyblogp4.payload.PostResponse;

import java.util.List;

public interface PostService {
    PostDto savePost(PostDto post);

    void deletePostById(long id);

    PostDto findPostById(long id);

    PostDto updatePostById(long id, PostDto postDto);

    PostResponse getPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
