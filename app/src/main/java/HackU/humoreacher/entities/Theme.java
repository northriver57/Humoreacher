package HackU.humoreacher.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "themes")
public class Theme {

    @PrimaryKey(autoGenerate = true)
    private int id;  // 自動生成されるID

    @ColumnInfo(name = "user_id")
    private String userId;  // 生徒のID

    @ColumnInfo(name = "selected_theme")
    private String selectedTheme;  // 生徒が選んだ次回のテーマ

    // コンストラクタ、ゲッター、セッターを追加
    public Theme(String userId, String selectedTheme) {
        this.userId = userId;
        this.selectedTheme = selectedTheme;
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

    public String getSelectedTheme() {
        return selectedTheme;
    }

    public void setSelectedTheme(String selectedTheme) {
        this.selectedTheme = selectedTheme;
    }
}
