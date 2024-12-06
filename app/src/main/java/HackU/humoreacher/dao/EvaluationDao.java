package HackU.humoreacher.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import HackU.humoreacher.entities.Evaluation;

@Dao
public interface EvaluationDao {

    @Insert
    void insert(Evaluation evaluation);

    @Query("SELECT AVG(rating) FROM evaluations WHERE user_id = :userId")
    float getAverageRating(String userId);  // 平均評価を取得

    @Query("SELECT * FROM evaluations WHERE user_id = :userId")
    List<Evaluation> getEvaluationsByUser(String userId);  // 特定のユーザーの評価を取得
}
