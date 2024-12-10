package HackU.humoreacher.database;

import android.content.Context;
import android.util.Log;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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
            Log.d("AppDatabase", "Creating a new instance of the database");
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "users_database")
                    .fallbackToDestructiveMigration()
                    .build();
        } else {
            Log.d("AppDatabase", "Database instance already exists");
        }
        return instance;
    }
}
