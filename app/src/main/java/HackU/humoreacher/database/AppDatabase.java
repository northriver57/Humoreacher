package HackU.humoreacher.database;
import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.room.RoomDatabase;
import androidx.annotation.NonNull;

import HackU.humoreacher.dao.EvaluationDao;
import HackU.humoreacher.dao.ThemeDao;
import HackU.humoreacher.dao.UserDao;
import HackU.humoreacher.entities.Evaluation;
import HackU.humoreacher.entities.Theme;
import HackU.humoreacher.entities.User;

@Database(entities = {User.class, Evaluation.class, Theme.class}, version = 4, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao userDao();
    public abstract EvaluationDao evaluationDao();
    public abstract ThemeDao themeDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "humoreacher_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(new Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            // 初期テーマデータを挿入
                            new Thread(() -> {
                                AppDatabase database = getInstance(context);
                                ThemeDao themeDao = database.themeDao();
                                String[] initialThemes = {
                                        "ゲーム", "勉強", "スポーツ", "友達との関係", "健康",
                                        "未来の夢", "音楽", "映画・ドラマ", "趣味", "家族",
                                        "環境問題", "お金の使い方", "科学技術", "学校生活", "社会貢献活動"
                                };
                                for (String themeName : initialThemes) {
                                    themeDao.insert(new Theme(themeName, 0));
                                }
                            }).start();
                        }
                    })
                    .build();
        }
        return instance;
    }
}
