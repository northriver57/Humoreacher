package HackU.humoreacher.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import HackU.humoreacher.entities.Theme;

@Dao
public interface ThemeDao {

    // 新しいテーマを挿入
    @Insert
    void insertTheme(Theme theme);

    // 全てのテーマを選択数順に取得
    @Query("SELECT * FROM themes ORDER BY selectionCount DESC")
    List<Theme> getAllThemesOrderedBySelectionCount();

    // 特定のテーマの選択数を更新
    @Query("UPDATE themes SET selectionCount = selectionCount + 1 WHERE name = :themeName")
    void incrementSelectionCount(String themeName);
}
