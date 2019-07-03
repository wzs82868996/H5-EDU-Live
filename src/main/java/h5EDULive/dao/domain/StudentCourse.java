package h5EDULive.dao.domain;

import h5EDULive.Util.Serialization;

import javax.persistence.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

@Entity
@Table(name = "stu_course")
public class StudentCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "stu_id")
    private int stuId;
    @Column(name = "course_id")
    private int courseId;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name="teacher",columnDefinition="longblob")
//    private User teacher;
    private byte[] teacher;

    public byte[] getByte(){
        return teacher;
    }

    public User getTeacher() {
        return (User) Serialization.byte2obj(teacher);
    }

    public void setTeacher(User ateacher) {
        this.teacher=Serialization.obj2byte(ateacher);
    }

    public StudentCourse(){}

    public int getId() {
        return id;
    }

    public int getStuId() {
        return stuId;
    }

    public void setStuId(int stuId) {
        this.stuId = stuId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }


}