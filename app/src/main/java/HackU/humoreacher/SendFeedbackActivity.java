package HackU.humoreacher;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SendFeedbackActivity extends AppCompatActivity {

    private EditText feedbackEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_feedback);

        feedbackEditText = findViewById(R.id.feedbackEditText);
    }

    public void onSendFeedback(View view) {
        String feedback = feedbackEditText.getText().toString().trim();

        if (feedback.isEmpty()) {
            Toast.makeText(this, "感想を入力してください", Toast.LENGTH_SHORT).show();
        } else {
            // フィードバックをデータベースに保存する処理
            new Thread(() -> {
                // 例: appDatabase.feedbackDao().insertFeedback(new Feedback(feedback));
                runOnUiThread(() -> {
                    Toast.makeText(SendFeedbackActivity.this, "感想が送信されました", Toast.LENGTH_SHORT).show();
                    finish(); // 送信後、画面を閉じる
                });
            }).start();
        }
    }
}
