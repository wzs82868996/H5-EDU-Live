package h5EDULive.dao;

import h5EDULive.dao.domain.Post;
import h5EDULive.dao.domain.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostResponseRepository extends JpaRepository<PostResponse, Integer>, JpaSpecificationExecutor<Post> {
<<<<<<< HEAD

=======
>>>>>>> 4f97aca82a6a38af1c1af482d678d0449af70477

    Page<PostResponse> findAllByPostId(int postId, Pageable pageable);

    Post findByPostId(int postId);

    void deleteByResId(int resId);
}
