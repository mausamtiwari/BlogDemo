package be.intecbrussel.jpaonetomanydemo.service;

import be.intecbrussel.jpaonetomanydemo.model.Post;

import java.util.List;

public interface PostService {
    List<Post> getAllPost();
    void savePost(Post post);

    Post getPostById(Long postId);

    void deletePostById(Long postId);
}


