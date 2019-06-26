package h5EDULive.dao;

import h5EDULive.web.dto.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer>, JpaSpecificationExecutor<Course> {

    Course findByCourseId(int id);

    Course findByName(String name);


    @Query(value = "select name from course natural join stu_Course where stu_id=?1",nativeQuery = true)
    List<String> findNameById(int id);

    void deleteByCourseId(int id);

    @Query(value = "update course set name=?2 where course_id=?1",nativeQuery = true)
    void updateNameByCourseId(int courseId,String name);

    @Query(value = "update course set depiction=?2 where course_id=?1",nativeQuery = true)
    void updateDepicitionByCourseId(int courseId,String depiction);

    @Query(value = "update course set label=?2 where course_id=?1",nativeQuery = true)
    void updateLabelByCourseId(int courseId,String label);



}
