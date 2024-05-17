package be.intecbrussel.jpaonetomanydemo.controller;

import be.intecbrussel.jpaonetomanydemo.model.Comment;
import be.intecbrussel.jpaonetomanydemo.model.Post;
import be.intecbrussel.jpaonetomanydemo.service.CommentServiceImpl;
import be.intecbrussel.jpaonetomanydemo.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class CommentController {
    private  CommentServiceImpl commentService;
    private  PostServiceImpl postService;

    @Autowired
    public CommentController(CommentServiceImpl commentService, PostServiceImpl postService) {
        this.commentService = commentService;
        this.postService = postService;
    }


    /*@GetMapping("/posts/{postId}/comments")
    public String getAllCommentsByPostId(@PathVariable(value = "postId") Long postId, Model model) {
        model.addAttribute("comments", commentService.getAllCommentsByPostID(postId));
        return "index";
    }*/

    @GetMapping("/posts/{postId}/comments")
    public String getAllCommentsByPostId(@PathVariable(value = "postId") Long postId, Model model) {
        model.addAttribute("comments", commentService.getAllCommentsByPostId(postId));
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        model.addAttribute("comment", new Comment());
        return "view_comments";
    }


  /*  @PostMapping("/posts/{postId}/comments")
    public String createComment(@PathVariable(value = "postId") Comment comment) {
        commentService.saveComment(comment);
        return "redirect:/";
    }*/

    @PostMapping("/posts/{postId}/comments")
    public String createComment(@PathVariable(value = "postId") Long postId, @ModelAttribute("comment") Comment comment) {
        Post post = postService.getPostById(postId);
        comment.setPost(post);
        commentService.saveComment(comment);
        return "redirect:/posts/" + postId + "/comments";
    }

    @GetMapping("/posts/{postId}/comments/{commentId}/edit")
    public String showEditCommentForm(@PathVariable(value = "postId") Long postId,
                                      @PathVariable(value = "commentId") Long commentId, Model model) {
        Comment comment = commentService.getCommentById(commentId);
        model.addAttribute("comment", comment);
        model.addAttribute("postId", postId);
        return "edit_comment";
    }

    @PostMapping("/posts/{postId}/comments/{commentId}/edit")
    public String updateComment(@PathVariable(value = "postId") Long postId,
                                @PathVariable(value = "commentId") Long commentId,
                                @ModelAttribute("comment") Comment comment) {
        Comment existingComment = commentService.getCommentById(commentId);
        existingComment.setText(comment.getText());
        commentService.saveComment(existingComment);
        return "redirect:/posts/" + postId + "/comments";
    }


    @GetMapping("/posts/{postId}/comments/{commentId}/delete")
    public String deleteComment(@PathVariable(value = "postId") Long postId,
                                @PathVariable(value = "commentId") Long commentId) {
        commentService.deleteCommentById(commentId);
        return "redirect:/posts/" + postId + "/comments";
    }

    /*@PutMapping("/posts/{postId}/comments/{commentId}")
    public Comment updateComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId, @Valid @RequestBody Comment commentRequest) {
        if (!postRepository.existsById(postId)) {
            throw new ResourceNotFoundException("PostId " + postId + " not found");
        }
        return commentRepository.findById(commentId).map(comment -> {
            comment.setText(commentRequest.getText());
            return commentRepository.save(comment);
        }).orElseThrow(() -> new ResourceNotFoundException("CommentId " + commentId + "not found"));
    }

    @DeleteMapping("/posts/{postId}/comments/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId) {
        return commentRepository.findByIdAndPostId(commentId, postId).map(comment -> {
            commentRepository.delete(comment);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("Comment not found with id " + commentId + " and postId " + postId));
    }*/
};

