package be.intecbrussel.jpaonetomanydemo.service;

import be.intecbrussel.jpaonetomanydemo.model.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> getAllComments();
    void saveComment(Comment comment);
    Comment getCommentById(Long id);
    void deleteCommentById(Long id);
    List<Comment> getAllCommentsByPostId(Long postId);
    //void updateComment(Comment comment);
}
