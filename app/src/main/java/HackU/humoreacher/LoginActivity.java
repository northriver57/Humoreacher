package HackU.humoreacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.entities.User;
import HackU.humoreacher.dao.UserDao;

public class LoginActivity extends AppCompatActivity {

    private EditText userIdEditText;
    private EditText passwordEditText;
    private AppDatabase db;
    private UserDao userDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIdEditText = findViewById(R.id.userIdEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        // Roomデータベースを初期化
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "app_database")
                .allowMainThreadQueries()  // メインスレッドでの操作を許可（本番では非推奨）
                .build();
        userDao = db.userDao();
    }

    // ログインボタンが押されたときの処理
    public void onLogin(View view) {
        String userId = userIdEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // ユーザーIDとパスワードがデータベースに存在するか確認
        User user = userDao.getUserByIdAndPassword(userId, password);

        if (user != null) {
            // ログイン成功した場合、生徒か校長かに関係なく画面遷移
            startActivity(new Intent(this, MainActivity.class));  // 共通の画面に遷移
        } else {
            // ログイン失敗
            Toast.makeText(this, "IDまたはパスワードが間違っています", Toast.LENGTH_SHORT).show();
        }
    }
}
