package h5EDULive.dao;

import com.h5_sdu_live.demo.domain.StuCourseMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface StudentCourseRepository extends JpaRepository<StuCourseMapper, Integer>, JpaSpecificationExecutor<StuCourseMapper> {
    List<StuCourseMapper> findByStuId(int id);
    void deleteByCourseIdAndStuId(int courseId, int stuId);
}