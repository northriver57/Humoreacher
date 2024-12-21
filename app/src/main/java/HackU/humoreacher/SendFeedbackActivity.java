package HackU.humoreacher;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.entities.Evaluation;

public class SendFeedbackActivity extends AppCompatActivity {

    private EditText feedbackEditText;
    private Button sendButton;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        // 各ビューの初期化
        feedbackEditText = findViewById(R.id.feedbackEditText);
        sendButton = findViewById(R.id.sendButton);

        // AppDatabaseのインスタンス取得
        appDatabase = AppDatabase.getInstance(this);
    }

    // 送信ボタンがクリックされたときの処理
    public void onSendFeedback(View view) {
        String feedback = feedbackEditText.getText().toString().trim();

        // 感想が空または空白のみの場合は送信を防止
        if (feedback.isEmpty()) {
            Toast.makeText(this, "感想を入力してください", Toast.LENGTH_SHORT).show();
            return;
        }

        // 生徒IDはログイン情報から取得するか、引き継ぐ必要がある
        String userId = "studentId"; // 仮のID。実際はログインした生徒のIDを使用

        // 評価（☆）の情報は他の画面から引き継ぐか、データベースから取得する
        float rating = 4.0f;  // 仮の評価。実際の評価は別途取得

        // 評価と感想をデータベースに保存
        Evaluation evaluation = new Evaluation(userId, rating, feedback);

        // データベースに保存
        new Thread(() -> {
            appDatabase.evaluationDao().insertEvaluation(evaluation);
            runOnUiThread(() -> {
                Toast.makeText(SendFeedbackActivity.this, "感想が送信されました", Toast.LENGTH_SHORT).show();
                finish(); // 送信後に前の画面に戻る
            });
        }).start();
    }

    public void onBackButtonClicked(View view) {
        onBackPressed(); // 標準的な戻る処理
    }
}
