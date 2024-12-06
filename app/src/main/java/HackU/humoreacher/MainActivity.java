package HackU.humoreacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.dao.UserDao;

public class MainActivity extends AppCompatActivity {

    private AppDatabase db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Roomデータベースの初期化
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app_database")
                .allowMainThreadQueries()  // メインスレッドでの操作を許可（本番では非推奨）
                .build();
        userDao = db.userDao();
    }

    // ログイン画面に遷移
    public void goToLogin(View view) {
        // ルームが存在しない場合、ログイン画面に遷移しない
        if (userDao.getAllUsers().isEmpty()) {
            Toast.makeText(this, "ルームがありません", Toast.LENGTH_SHORT).show();
            return;  // それ以上の処理を行わない
        }

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    // ルーム作成画面に遷移
    public void goToRoomCreate(View view) {
        Intent intent = new Intent(this, RoomCreateActivity.class);
        startActivity(intent);
    }
}
