package HackU.humoreacher.database;


import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import HackU.humoreacher.dao.EvaluationDao;
import HackU.humoreacher.dao.ThemeDao;
import HackU.humoreacher.dao.UserDao;
import HackU.humoreacher.entities.User;
import HackU.humoreacher.entities.Evaluation;
import HackU.humoreacher.entities.Theme;

@Database(entities = {User.class, Evaluation.class, Theme.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract UserDao userDao();
    public abstract EvaluationDao evaluationDao();
    public abstract ThemeDao themeDao();

    private static volatile AppDatabase INSTANCE;

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "app_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
