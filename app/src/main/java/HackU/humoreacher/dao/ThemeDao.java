package HackU.humoreacher.dao;

import androidx.room.Dao;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ThemeDao {

    @Query("SELECT selected_theme, COUNT(*) as count FROM themes GROUP BY selected_theme")
    List<ThemeCount> getThemeCount();  // `ThemeCount`を使ってカラムをマッピング
}
