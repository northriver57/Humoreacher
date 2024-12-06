package HackU.humoreacher;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.entities.User;
import HackU.humoreacher.dao.UserDao;

public class RoomCreateActivity extends AppCompatActivity {

    private EditText userIdEditText;
    private EditText passwordEditText;
    private EditText adminPasswordEditText;
    private AppDatabase db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        userIdEditText = findViewById(R.id.userIdEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        adminPasswordEditText = findViewById(R.id.adminPasswordEditText);

        // Roomデータベースを初期化
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app_database")
                .allowMainThreadQueries()  // メインスレッドでの操作を許可（本番では非推奨）
                .build();
        userDao = db.userDao();
    }

    // ルーム作成ボタンが押されたときの処理
    public void onCreateRoom(View view) {
        String userId = userIdEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String adminPassword = adminPasswordEditText.getText().toString();

        // 管理者パスワードのチェック
        if (adminPassword.equals("admin123")) {
            // 新しいユーザーをデータベースに追加
            User user = new User(userId, password);
            userDao.insert(user);
            Toast.makeText(this, "ルームが作成されました", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "管理者パスワードが間違っています", Toast.LENGTH_SHORT).show();
        }
    }
}
