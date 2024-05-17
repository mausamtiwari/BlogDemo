package be.intecbrussel.jpaonetomanydemo.controller;

import be.intecbrussel.jpaonetomanydemo.model.Post;
import be.intecbrussel.jpaonetomanydemo.service.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.ModelAndView;


@Controller
public class PostController {

    private PostServiceImpl postService;

    @Autowired
    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String viewHomepage(Model model) {
        model.addAttribute("ListPosts", postService.getAllPost());
        return "index";
    }

     @GetMapping("/showNewPostForm")
      public ModelAndView showNewPostForm() {
          Post post = new Post();
          ModelAndView modelAndView = new ModelAndView("new_post"); // Set view name
          modelAndView.addObject("post", post);
          return modelAndView;
      }


   /* @GetMapping("/showNewPostForm")
    public String showNewPostForm(Model model) {
        model.addAttribute("post", new Post());
        return "new_post";
    }*/

    @PostMapping("/createPost")
    public String createPost(@ModelAttribute("post") Post post) {
        postService.savePost(post);
        return "redirect:/";
    }

    @GetMapping("/updatePost/{id}")
    public String showUpdatePostForm(@PathVariable(value = "id") Long postId, Model model) {
        Post post = postService.getPostById(postId);
        model.addAttribute("post", post);
        return "edit_post";
    }

    @PostMapping("/updatePost/{id}")
    public String updatePost(@PathVariable(value="id") Long postId, @ModelAttribute("post") Post post){
        Post existingPost = postService.getPostById(postId);
        existingPost.setTitle(post.getTitle());
        existingPost.setDescription(post.getDescription());
        existingPost.setContent(post.getContent());
        postService.savePost(existingPost);
        return "redirect:/";
    }

    @GetMapping("/deletePost/{id}")
    public String deletePost(@PathVariable(value = "id") Long postId) {
        postService.deletePostById(postId);
        return "redirect:/";
    }


}


