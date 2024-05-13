package be.intecbrussel.jpaonetomanydemo.config;

import be.intecbrussel.jpaonetomanydemo.model.Comment;
import be.intecbrussel.jpaonetomanydemo.model.Post;
import be.intecbrussel.jpaonetomanydemo.repository.PostRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostConfig {

    @Bean
    CommandLineRunner clr(PostRepository repository) {

        return (args) -> {
            // Create a Post
            Post post = new Post("postTitlesss", "post description", "post content"); // Create Comments
            Comment comment1 = new Comment("Great Post!");
            comment1.setPost(post);
            Comment comment2 = new Comment("Really helpful Post. Thanks a lot!");
            comment2.setPost(post); // Add comments in the
            post.getComments().add(comment1);
            post.getComments().add(comment2);
            repository.save(post);
        };
    }

}
