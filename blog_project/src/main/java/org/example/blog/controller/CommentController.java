package org.example.blog.controller;

import org.example.blog.dto.CommentDto;
import org.example.blog.entity.Comment;
import org.example.blog.service.CommentService;
import org.example.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("comment")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @Autowired
    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<CommentDto>> findAll(){
        return ResponseEntity.ok(commentService.findAll()
                .stream()
                .map(this::fromComment)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> findById(@PathVariable Long id){
        return ResponseEntity.ok(
                fromComment(commentService.findById(id)));
    }

    @PostMapping
    public ResponseEntity<CommentDto> create(@RequestBody CommentDto commentDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fromComment(commentService.create(commentDto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> update(@PathVariable Long id,
                                             @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(fromComment(commentService.update(id,commentDto)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){
        commentService.delete(id);
    }
    private CommentDto fromComment(Comment comment){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getCommentId());
        commentDto.setContent(comment.getContent());
        commentDto.setUser(userService.getUsername(comment.getUserId()));
        commentDto.setDtCreated(comment.getDtCreated());
        commentDto.setDtUpdated(comment.getDtUpdate());
        commentDto.setPostId(comment.getPost().getPostId());

        return commentDto;
    }
}
