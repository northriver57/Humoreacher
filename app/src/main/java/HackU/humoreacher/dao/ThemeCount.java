package HackU.humoreacher.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;

@Entity(tableName = "theme_count")
public class ThemeCount {

    @ColumnInfo(name = "selected_theme")
    private String selectedTheme;  // selected_themeカラムをマッピング

    @ColumnInfo(name = "count")
    private int count;  // countカラムをマッピング

    // コンストラクタ
    public ThemeCount(String selectedTheme, int count) {
        this.selectedTheme = selectedTheme;
        this.count = count;
    }

    // ゲッターとセッター
    public String getSelectedTheme() {
        return selectedTheme;
    }

    public void setSelectedTheme(String selectedTheme) {
        this.selectedTheme = selectedTheme;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
