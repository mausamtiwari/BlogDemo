package be.intecbrussel.jpaonetomanydemo.service;

import be.intecbrussel.jpaonetomanydemo.model.Comment;
import be.intecbrussel.jpaonetomanydemo.model.Post;
import be.intecbrussel.jpaonetomanydemo.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

  /*  @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }*/


   /* @Override
    public List<Comment> getAllCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }
*/
    @Override
    public void saveComment(Comment comment) {
        commentRepository.save(comment);
    }


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

    @Override
    public Page<Comment> findCommentPaginated(Long postId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return commentRepository.findByPostId(postId, pageable);
    }
}


