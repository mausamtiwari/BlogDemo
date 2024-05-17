package be.intecbrussel.jpaonetomanydemo.service;

import be.intecbrussel.jpaonetomanydemo.model.Comment;
import be.intecbrussel.jpaonetomanydemo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }


   /* public List<Comment> getAllCommentsByPostID(Long id) {
        return commentRepository.findAll().stream().filter(c->c.getPost().getId()==id).toList();
    }*/

    @Override
    public List<Comment> getAllCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }

   /* @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if(!commentOptional.isPresent()){
            throw new IllegalStateException("Comment not found");
        }
        return commentOptional.get();
    }*/

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> commentOptional = commentRepository.findById(id);
        if (!commentOptional.isPresent()) {
            throw new IllegalStateException("Comment not found");
        }
        return commentOptional.get();
    }

    @Override
    public void deleteCommentById(Long id) {
        boolean exists = commentRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("Comment id " + id + " not found");
        }
        commentRepository.deleteById(id);
    }


   /* @Override
    public void updateComment(Comment comment) {
        boolean exists = commentRepository.existsById(comment.getId());
        if (!exists) {
            throw new IllegalStateException("Comment id " + comment.getId() + " not found");
        }
        commentRepository.save(comment);
    }*/
  /*  @Override
    public void deleteCommentById(Long id) {
        boolean exists = commentRepository.existsById(id);
        if(!exists){
            throw new IllegalStateException("Comment id "+id+" not found");
        }
        commentRepository.deleteById(id);
    }*/
}
