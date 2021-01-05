package MyBlog.demo;

import MyBlog.demo.controller.PostController;
import MyBlog.demo.domain.Post;
import MyBlog.demo.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class ControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PostService postService;

    @Test
    public void findByPost() throws Exception {
        given(this.postService.findOne(anyLong()).willReturn(new Post("제목", "컨텐츠"));
        MvcResult mvcResult = this.mvc.perform(get("/posts/{id}", 1))
                .andReturn();

        Post post = (Post) mvcResult.getModelAndView().getModel().get("post");
        assertThat(post.getTitle()).isEqualTo("제목");
        assertThat(post.getContent()).isEqualTo("컨텐츠");

    }

    @Test
    public void newPost() throws Exception {
        this.mvc.perform(get("/posts/new"))
                .andExpect(view().name("post/new"))
                .andReturn();
    }

    private MockBean view() {
    }

    @Test
    public void createPost() throws Exception {
        Post post = new Post(1L, "제목1", "컨텐츠1");
        given(postService.createPost(any())).willReturn(post);

        this.mvc.perform(post("/posts")
                .param("title","제목1")
                .param("content","컨텐츠1")
                .andExpect(header().string(HttpHeaders.LOCATION, "/posts/1"));
    }

    @Test
    public void createPostValid() throws Exception {
        this.mvc.perform(post("/posts")
                .param("title","제목1")
                .andExpect(view().name("post/new"));

    }

}
