package HackU.humoreacher.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import HackU.humoreacher.entities.Theme;

@Dao
public interface ThemeDao {

    // テーマを選択回数で並べ替えて取得
    @Query("SELECT * FROM themes ORDER BY selectionCount DESC")
    List<Theme> getAllThemesOrderedBySelectionCount();

    // 指定されたテーマ名でテーマを取得
    @Query("SELECT * FROM themes WHERE name = :themeName LIMIT 1")
    Theme getThemeByName(String themeName);

    // テーマの選択回数をインクリメント
    @Query("UPDATE themes SET selectionCount = selectionCount + 1 WHERE name = :themeName")
    void incrementSelectionCount(String themeName);

    @Insert
    void insert(Theme theme);

    @Update
    void update(Theme theme);
}
