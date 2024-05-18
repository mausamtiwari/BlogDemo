package be.intecbrussel.jpaonetomanydemo.controller;

import be.intecbrussel.jpaonetomanydemo.model.Post;
import be.intecbrussel.jpaonetomanydemo.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
public class PostController {

    private PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    // Handles the root URL, redirecting to the first page of paginated posts
    @GetMapping("/")
    public String viewHomepage(Model model) {
       return findPostPaginated(1,model);
    }

    // Shows the form to create a new post
    @GetMapping("/showNewPostForm")
    public String showNewPostForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "new_post";
    }

    // Handles form submission for creating a new post
    @PostMapping("/createPost")
    public String createPost(@ModelAttribute("post") Post post) {
        postService.savePost(post);
        return "redirect:/";
    }

    // Shows the form to update an existing post
    @GetMapping("/updatePost/{id}")
    public String showUpdatePostForm(@PathVariable(value = "id") Long postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "edit_post";
    }

    // Handles form submission for updating an existing post
    @PostMapping("/updatePost/{id}")
    public String updatePost(@PathVariable(value = "id") Long postId, @ModelAttribute("post") Post post) {
        Post existingPost = postService.getPostById(postId);
        existingPost.setTitle(post.getTitle());
        existingPost.setDescription(post.getDescription());
        existingPost.setContent(post.getContent());
        postService.savePost(existingPost);
        return "redirect:/";
    }

    // Handles deleting a post by its ID
    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable(value = "id") Long postId) {
        postService.deletePostById(postId);
        return "redirect:/";
    }

    // Handles pagination for displaying posts
    @GetMapping("/page/{pageNo}")
    public String findPostPaginated(@PathVariable(value ="pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<Post> page = postService.findPostPaginated(pageNo, pageSize);
        List<Post> postList = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements()); // returns the number of elements
        model.addAttribute("postList", postList);
        return "index";
    }


}


