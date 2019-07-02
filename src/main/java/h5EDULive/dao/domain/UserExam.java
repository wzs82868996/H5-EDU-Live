package h5EDULive.dao.domain;

import h5EDULive.Util.Serialization;

import javax.persistence.Entity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "user_exam")
public class UserExam {
//    public UserIdPK getId() {
//        return id;
//    }
//
//    public void setId(UserIdPK id) {
//        this.id = id;
//    }
//
//    @EmbeddedId
//    private UserIdPK id;

    //    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int userId;

    @Column(name = "course_id")
    private int courseId;

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name="answers",columnDefinition="longblob")
    private byte[] answers;//用户的解答

    @Lob
    @Basic(fetch=FetchType.LAZY)
    @Column(name="sub_score",columnDefinition="blob")
    private byte[] subScore;

    @Column(name = "total_score")
    private int totalScore;

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
    public  Object byte2obj(byte[] bytes) throws Exception {
        Object ret = null;
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes);
        ObjectInputStream in = new ObjectInputStream(bais);
        ret = in.readObject();
        in.close();
        return ret;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List<Integer> getAnswers() throws Exception{
        return (List<Integer>)byte2obj(answers);
    }

    public void setAnswers(List<Integer> answers) throws Exception {
        this.answers = obj2byte(answers);
    }

    public List<Integer> getSubScore() throws Exception {
        return (List<Integer>)byte2obj(subScore);
    }

    public void setSubScore(List<Integer> subScore) throws Exception {
        this.subScore = obj2byte(subScore);
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public String message(){
        return getCourseId()+" "+getUserId()+" "+getTotalScore();
    }

}