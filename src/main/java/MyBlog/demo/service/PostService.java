package MyBlog.demo.service;

import MyBlog.demo.domain.Post;
import MyBlog.demo.repository.PostRepository;
import javassist.NotFoundException;

import java.time.LocalDateTime;

public class PostService {
    private PostRepository postRepository;

    //게시글작성
    public Post createPost(Post post) {
        post.setRegDate(LocalDateTime.now());
        return postRepository.save(post);
    }

    //글삭제
    /*public void deletePost(Long id) {
        Post oldPost = postRepository.findById(Long id);
        oldPost.
    }*/

    //조회
    public Post findOne(Long id) throws NotFoundException {
        Post post = postRepository.findOne(id);
        if(post==null) {
            throw new NotFoundException(id + " not found");
        }
        return post;
    }
}
