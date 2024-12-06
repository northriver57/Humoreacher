package HackU.humoreacher;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
public class LoginActivity extends AppCompatActivity {

    private EditText userIdEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userIdEditText = findViewById(R.id.userIdEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
    }

    // 完了ボタンが押されたときの処理
    public void onComplete(View view) {
        String userId = userIdEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        // ユーザーIDとパスワードを確認する処理を追加
        if (userId.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "ユーザーIDまたはパスワードを入力してください", Toast.LENGTH_SHORT).show();
        } else {
            // ログイン成功の処理（例: メイン画面に遷移）
            Toast.makeText(this, "ログイン成功", Toast.LENGTH_SHORT).show();
            // 例: メイン画面に遷移
            // startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
    }

    // 戻るボタンが押されたときの処理
    public void onBackButtonClicked(View view) {
        onBackPressed(); // 戻るボタンが押された時に onBackPressed() を呼び出す
    }
}
