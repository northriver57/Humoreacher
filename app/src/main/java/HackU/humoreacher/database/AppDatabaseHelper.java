package HackU.humoreacher.database;

import android.content.Context;
import androidx.room.Room;

public class AppDatabaseHelper {

    private static AppDatabase appDatabase;

    public static AppDatabase getDatabase(Context context) {
        if (appDatabase == null) {
            appDatabase = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
                    .allowMainThreadQueries()  // メインスレッドでの操作を許可（本番では非推奨）
                    .build();
        }
        return appDatabase;
    }
}
