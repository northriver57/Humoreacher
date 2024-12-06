package HackU.humoreacher.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import HackU.humoreacher.dao.UserDao;
import HackU.humoreacher.entities.User;

@Database(entities = {User.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract UserDao userDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "users_database")
                    .fallbackToDestructiveMigration()  // バージョン変更時にデータを破棄
                    .build();
        }
        return instance;
    }
}
