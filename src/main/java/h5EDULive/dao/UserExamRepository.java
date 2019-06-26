package h5EDULive.dao;

import h5EDULive.web.dto.UserExam;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserExamRepository extends JpaRepository<UserExam, Integer>, JpaSpecificationExecutor<UserExam> {
    @Query(value = "select * from user_exam where user_id=?1", nativeQuery = true)
    Page<UserExam> findByUserId(int userId);

    UserExam findByUserIdAndCourseId(int userId, int courseId);
}
