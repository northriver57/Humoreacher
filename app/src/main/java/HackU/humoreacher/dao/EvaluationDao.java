package HackU.humoreacher.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
import HackU.humoreacher.entities.Evaluation;

@Dao
public interface EvaluationDao {

    // 新しい評価を挿入
    @Insert
    void insertEvaluation(Evaluation evaluation);

    // 特定のユーザーの評価を取得
    @Query("SELECT * FROM evaluations WHERE user_id = :userId")
    List<Evaluation> getEvaluationsByUserId(String userId);

    // 全ての評価を取得（必要に応じて）
    @Query("SELECT * FROM evaluations")
    List<Evaluation> getAllEvaluations();

    // 平均評価を取得
    @Query("SELECT AVG(rating) FROM evaluations")
    float getAverageRating(); // 評価の平均を取得
}
