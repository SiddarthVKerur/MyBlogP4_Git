package com.emyblogp4.Controller;

import com.emyblogp4.Service.PostService;
import com.emyblogp4.payload.PostDto;
import com.emyblogp4.payload.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
public class PostRestController {
    @Autowired
    private PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<PostDto> savePost(@RequestBody PostDto dto) {
        PostDto postDto = postService.savePost(dto);
        return new ResponseEntity<>(postDto, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable long id) {
        postService.deletePostById(id);
        return new ResponseEntity<>("post is deleted", HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<PostDto> findPost(@PathVariable long id) {
        PostDto postDto = postService.findPostById(id);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<PostDto> updatePost(@PathVariable long id, @RequestBody PostDto postDto) {
        PostDto dto = postService.updatePostById(id, postDto);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<PostResponse> getAllPost(@RequestParam(value = "pageNo", required = false, defaultValue = "0") int pageNo, @RequestParam(value = "pageSize", required = false, defaultValue = "3") int pageSize, @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy, @RequestParam(value = "sortDir", required = false, defaultValue = "asc") String sortDir) {
        PostResponse postResponse = postService.getPosts(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
}
