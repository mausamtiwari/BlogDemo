package be.intecbrussel.jpaonetomanydemo.service;

import be.intecbrussel.jpaonetomanydemo.model.Post;
import be.intecbrussel.jpaonetomanydemo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public void savePost(Post post) {
        postRepository.save(post);
    }

    @Override
    public Post getPostById(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.isPresent()) {
            throw new IllegalStateException("Post not found");
        }
        return postOptional.get();
    }
    @Override
    public void deletePostById(Long postId) {
        postRepository.deleteById(postId);

    }

    @Override
    public Page<Post> findPostPaginated(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo-1, pageSize);
        return this.postRepository.findAll(pageable);
    }
}


