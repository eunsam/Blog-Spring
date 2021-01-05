package MyBlog.demo.controller;

import MyBlog.demo.domain.Post;
import MyBlog.demo.repository.PostDto;
import MyBlog.demo.service.PostService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class PostController {

    private PostService postService;

    @GetMapping("/{id}")
    public String findByPost(@PathVariable Long id, Model model) throws NotFoundException {
        Post post = postService.findOne(id);
        if (post == null) {
            throw new NotFoundException(id+" not found");
        }
        model.addAttribute("post", post);
        return "post/post";
    }

    @GetMapping("/new")
    public String newPost(PostDto postDto) {
        return "post/new";
    }

    @PostMapping
    public String createPost(@ModelAttribute @Valid PostDto createPost, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "post/new";
        }

        Post post = new Post(createPost.getTitle(),
                createPost.getContent());
        Post newPost = postService.createPost(post);
        model.addAttribute("post", newPost);
        return "redirect:/posts/" + newPost.getId();
    }
}
