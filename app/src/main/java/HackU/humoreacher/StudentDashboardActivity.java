package HackU.humoreacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.entities.Evaluation;
import HackU.humoreacher.entities.User;

public class StudentDashboardActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private Button okButton, sendFeedbackButton, selectThemeButton, logoutButton;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        // RatingBarとボタンの初期化
        ratingBar = findViewById(R.id.ratingBar);
        okButton = findViewById(R.id.okButton);
        sendFeedbackButton = findViewById(R.id.sendFeedbackButton);
        selectThemeButton = findViewById(R.id.selectThemeButton);
        logoutButton = findViewById(R.id.logoutButton);

        // AppDatabaseのインスタンス取得
        appDatabase = AppDatabase.getInstance(this);
    }

    public void onSaveFeedback(View view) {
        float rating = ratingBar.getRating();

        if (rating == 0) {
            Toast.makeText(this, "評価を選んでください", Toast.LENGTH_SHORT).show();
            return;
        }

        String userId = "student123"; // ここで生徒のIDを取得（ログインした生徒のIDに変更）

        // 評価をデータベースに保存する処理
        new Thread(() -> {
            Evaluation evaluation = new Evaluation(userId, rating, ""); // ここで感想も渡せます
            appDatabase.evaluationDao().insertEvaluation(evaluation);

            runOnUiThread(() -> {
                Toast.makeText(StudentDashboardActivity.this, "評価が保存されました", Toast.LENGTH_SHORT).show();
            });
        }).start();
    }


    // 詳しい感想を送るボタン
    public void onSendFeedback(View view) {
        Intent intent = new Intent(StudentDashboardActivity.this, SendFeedbackActivity.class);
        startActivity(intent);
    }

    // テーマ選択画面に遷移
    public void onSelectTheme(View view) {
        Intent intent = new Intent(StudentDashboardActivity.this, SelectThemeActivity.class);
        startActivity(intent);
    }

    // ログアウトボタン
    public void onLogout(View view) {
        // ログイン画面に戻る処理（またはログアウト処理）
        Intent intent = new Intent(StudentDashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
