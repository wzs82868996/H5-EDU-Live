package h5EDULive.dao;

import h5EDULive.web.dto.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExamRepository extends JpaRepository<Exam, Integer>, JpaSpecificationExecutor<Exam> {
    Exam findByCourseId(int courseId);
}
