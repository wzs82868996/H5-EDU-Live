package h5EDULive.dao;

import h5EDULive.dao.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepository extends JpaRepository<Post, Integer>, JpaSpecificationExecutor<Post> {
    Page<Post> findAll(Pageable pageable);
    Post findByPostId(int postId);
    Page<Post> findByKeys(String pattern, Pageable pageable);
    void deleteByPostId(int postId);
}
