package h5EDULive.dao;

import h5EDULive.dao.domain.Post;
import h5EDULive.dao.domain.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ResponseRepository extends JpaRepository<Response, Integer>, JpaSpecificationExecutor<Post> {

    Response findByResId(int resId);

    void deleteByResId(int resId);

    void deleteByPId(int pId);
}
