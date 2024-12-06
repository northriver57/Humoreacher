package HackU.humoreacher.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "evaluations")
public class Evaluation {

    @PrimaryKey(autoGenerate = true)
    private int id;  // 自動生成されるID

    @ColumnInfo(name = "user_id")
    private String userId;  // 評価した生徒のID

    private float rating;  // ☆の評価（0～5）

    private String feedback;  // 生徒の感想

    // コンストラクタ、ゲッター、セッターを追加
    public Evaluation(String userId, float rating, String feedback) {
        this.userId = userId;
        this.rating = rating;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
