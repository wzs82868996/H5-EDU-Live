package h5EDULive.dao.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "teacher_course")
public class TeacherCourse implements Serializable {
    private final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int courseId;

    private int teacherId;

    public TeacherCourse(){}

    public int getId() {
        return id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }
}
