package be.intecbrussel.jpaonetomanydemo.service;

import be.intecbrussel.jpaonetomanydemo.model.Comment;
import be.intecbrussel.jpaonetomanydemo.model.Post;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {

    //List<Comment> getAllComments();
    void saveComment(Comment comment);
    Comment getCommentById(Long id);
    void deleteCommentById(Long id);
    //List<Comment> getAllCommentsByPostId(Long postId);

    Page<Comment> findCommentPaginated(Long postId, int pageNo, int pageSize);
}


