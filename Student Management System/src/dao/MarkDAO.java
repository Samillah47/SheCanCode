package dao;

import model.Mark;
import java.util.List;

public interface MarkDAO {
    void assignMark(Mark mark);
    List<Mark> getAllMarks();
    void updateMark(Mark mark);
    void deleteMark(int studentId, int courseId);
}
