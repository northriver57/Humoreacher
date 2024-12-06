package HackU.humoreacher;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.entities.User;

public class LoginActivity extends AppCompatActivity {

    private EditText userIdEditText;
    private EditText passwordEditText;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIdEditText = findViewById(R.id.userIdEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        // AppDatabaseからインスタンスを取得
        appDatabase = AppDatabase.getInstance(this);  // AppDatabase.getInstance(this)を使用
    }

    // 完了ボタンが押されたときの処理
    public void onComplete(View view) {
        String userId = userIdEditText.getText().toString().trim();  // 空白を除去
        String password = passwordEditText.getText().toString().trim();  // 空白を除去

        // ユーザーIDとパスワードが空でないかチェック
        if (userId.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "ログインIDまたはパスワードを入力してください", Toast.LENGTH_SHORT).show();
        } else {
            // 管理者ログインを先にチェック
            new Thread(() -> {
                User adminUser = appDatabase.userDao().getUserByIdAndAdminPassword(userId, password);
                runOnUiThread(() -> {
                    if (adminUser != null) {
                        // 管理者ログイン成功
                        Log.d("LoginActivity", "Admin user found: " + adminUser.getId());
                        Toast.makeText(LoginActivity.this, "管理者ログイン成功", Toast.LENGTH_SHORT).show();
                        // 管理者用ダッシュボードに遷移
                        Intent intent = new Intent(LoginActivity.this, PrincipalDashboardActivity.class);
                        startActivity(intent);
                        finish(); // ログイン画面を閉じる
                    } else {
                        // 一般ユーザーのログインチェック
                        new Thread(() -> {
                            User user = appDatabase.userDao().getUserByIdAndPassword(userId, password);
                            runOnUiThread(() -> {
                                if (user != null) {
                                    // 一般ユーザーログイン成功
                                    Log.d("LoginActivity", "User found: " + user.getId());
                                    Toast.makeText(LoginActivity.this, "ログイン成功", Toast.LENGTH_SHORT).show();
                                    // ユーザー用ダッシュボードに遷移
                                    Intent intent = new Intent(LoginActivity.this, StudentDashboardActivity.class);
                                    startActivity(intent);
                                    finish(); // ログイン画面を閉じる
                                } else {
                                    // ログイン失敗
                                    Log.d("LoginActivity", "User not found or wrong password");
                                    Toast.makeText(LoginActivity.this, "ユーザーIDまたはパスワードが間違っています", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }).start();
                    }
                });
            }).start();
        }
    }
}
