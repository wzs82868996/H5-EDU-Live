package h5EDULive.dao;

import h5EDULive.dao.domain.TeacherCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherCourseRepository extends JpaRepository<TeacherCourse, Integer>,JpaSpecificationExecutor<TeacherCourse> {

    void deleteByCourseId(int courseId);

    @Query(value = "select name from course where id in (select course_id from teacher_course where teacher_id=?1)",nativeQuery = true)
    List<String> getList(int id);

}
