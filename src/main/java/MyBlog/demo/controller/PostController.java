package MyBlog.demo.controller;

import MyBlog.demo.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class PostController {

    private PostService postService;

   // @GetMapping("/{id}")
   // public String findByPost
}
