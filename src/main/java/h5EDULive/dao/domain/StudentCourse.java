package h5EDULive.dao.domain;

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



    //将java对象序列化为byte
    public  byte[] obj2byte(Object obj) throws Exception {
        byte[] ret = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(baos);
        out.writeObject(obj);
        out.close();
        ret = baos.toByteArray();
        baos.close();
        return ret;
    }

    //将byte[]反序列化为java对象
    public static Object byte2obj(byte[] bytes) throws Exception {
        Object ret = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        ret = in.readObject();
        in.close();
        return ret;
    }

    public User getTeacher() throws Exception {
        return (User)byte2obj(teacher);
    }

    public void setTeacher(User ateacher) throws Exception {
        this.teacher=obj2byte(ateacher);
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