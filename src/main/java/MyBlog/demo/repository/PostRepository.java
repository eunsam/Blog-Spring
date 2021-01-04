package MyBlog.demo.repository;

import MyBlog.demo.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findOne(Long id);
}
