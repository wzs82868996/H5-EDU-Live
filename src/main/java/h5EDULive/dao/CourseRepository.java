package h5EDULive.dao;

import h5EDULive.dao.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Integer>, JpaSpecificationExecutor<Course> {
    Course findById(int id);

    Course findByName(String name);

    @Query(value = "select name from course where id in (select courseId from stu_Course where stuId=1)",nativeQuery = true)
    List<String> findNameById(int id);

    void deleteById(int id);

    @Query(value = "update course set name=?2 where id=?1",nativeQuery = true)
    void updateNameByCourseId(int courseId, String name);

    @Query(value = "update course set depiction=?2 where course_id=?1",nativeQuery = true)
    @Modifying
    void updateDepictionByCourseId(int courseId, String depiction);

    @Query(value = "update course set label=?2 where id=?1",nativeQuery = true)
    void updateLabelByCourseId(int courseId, String label);

    @Query(value = "select * from course where course_id in (select course_id from teacher_course where teacher_id=?1)",nativeQuery = true)
    List<Course> getList(int id);

}
