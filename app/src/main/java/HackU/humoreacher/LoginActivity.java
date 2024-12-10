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
        appDatabase = AppDatabase.getInstance(this);
    }

    // 完了ボタンが押されたときの処理
    public void onComplete(View view) {
        String userId = userIdEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        if (userId.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "ログインIDまたはパスワードを入力してください", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            // 管理者ログインの確認
            User adminUser = appDatabase.userDao().getUserByIdAndAdminPassword(userId, password);
            runOnUiThread(() -> {
                if (adminUser != null) {
                    // 管理者ログイン成功
                    Log.d("LoginActivity", "Admin user found: " + adminUser.getId());
                    Toast.makeText(LoginActivity.this, "管理者ログイン成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, PrincipalDashboardActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    // 一般ユーザーのログイン確認
                    new Thread(() -> {
                        User user = appDatabase.userDao().getUserByIdAndPassword(userId, password);
                        runOnUiThread(() -> {
                            if (user != null) {
                                // 一般ユーザーログイン成功
                                Log.d("LoginActivity", "User found: " + user.getId());
                                Toast.makeText(LoginActivity.this, "ログイン成功", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, StudentDashboardActivity.class);
                                startActivity(intent);
                                finish();
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

    // 戻るボタンが押されたときの処理
    public void onBackButtonClicked(View view) {
        onBackPressed(); // 標準的な戻る処理
    }
}
