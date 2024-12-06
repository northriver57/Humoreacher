package HackU.humoreacher;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.database.AppDatabaseHelper;
import HackU.humoreacher.entities.User;

public class LoginActivity extends AppCompatActivity {

    private EditText userIdEditText;
    private EditText passwordEditText;
    private AppDatabase appDatabase;  // インスタンス変数として appDatabase を宣言

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIdEditText = findViewById(R.id.userIdEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        // AppDatabaseHelper を使ってインスタンスを取得
        appDatabase = AppDatabaseHelper.getDatabase(this);
    }

    // 完了ボタンが押されたときの処理
    public void onComplete(View view) {
        String userId = userIdEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // ユーザーIDとパスワードを確認する処理を追加
        if (userId.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "ユーザーIDまたはパスワードを入力してください", Toast.LENGTH_SHORT).show();
        } else {
            // ユーザーをデータベースから取得して確認
            new Thread(() -> {
                User user = appDatabase.userDao().getUserByIdAndPassword(userId, password);
                runOnUiThread(() -> {
                    if (user != null) {
                        // ログイン成功
                        Toast.makeText(LoginActivity.this, "ログイン成功", Toast.LENGTH_SHORT).show();
                        // 例: メイン画面に遷移
                        // startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    } else {
                        // ログイン失敗
                        Toast.makeText(LoginActivity.this, "ユーザーIDまたはパスワードが間違っています", Toast.LENGTH_SHORT).show();
                    }
                });
            }).start();
        }
    }

    // 戻るボタンが押されたときの処理
    public void onBackButtonClicked(View view) {
        onBackPressed(); // 戻るボタンが押された時に onBackPressed() を呼び出す
    }
}
