package org.example.blog.controller;


import lombok.extern.slf4j.Slf4j;
import org.example.blog.dto.PostDto;
import org.example.blog.entity.Post;
import org.example.blog.entity.Tag;
import org.example.blog.service.PostService;
import org.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("post")
@Slf4j
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @Value("${eureka.instance.instanceId}")
    private String instanceId;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<PostDto>> findAll(@RequestParam Optional<String> query){
        return  ResponseEntity.ok(postService.findAll(query)
                .stream()
                .map(this::fromPost)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> findById(@PathVariable long postId){
        return ResponseEntity.ok(fromPost(postService.findById(postId)));
    }

    @PostMapping
    public ResponseEntity<PostDto> create(@RequestBody PostDto postDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fromPost(postService.create(postDto)));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> update(@PathVariable long postId,
                                          @RequestBody PostDto postDto){
        return ResponseEntity.ok(fromPost(postService.update(postId,postDto)));
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long postId){
        postService.delete(postId);
    }

    private PostDto fromPost(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getPostId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setTags(post.getTags()
                .stream()
                .map(Tag::getName)
                .sorted()
                .collect(Collectors.joining(" ")));
        postDto.setUser(userService.getUsername(post.getUserId()));
        postDto.setDtCreated(post.getDtCreated());
        postDto.setDtUpdated(post.getDtUpdated());

        return postDto;
    }
}
