package HackU.humoreacher;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.entities.Evaluation;

public class ReviewFeedbackActivity extends AppCompatActivity {

    private ListView feedbackListView;
    private AppDatabase appDatabase;
    private FeedbackAdapter feedbackAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_feedback);

        // ListViewの初期化
        feedbackListView = findViewById(R.id.feedbackListView);

        // AppDatabaseのインスタンス取得
        appDatabase = AppDatabase.getInstance(this);

        // 生徒の感想を取得し、アダプターを設定
        loadFeedbackData();
    }

    // 生徒の感想データをデータベースから取得
    private void loadFeedbackData() {
        new Thread(() -> {
            List<Evaluation> evaluations = appDatabase.evaluationDao().getAllEvaluations(); // すべての評価を取得

            // UIの更新はメインスレッドで行う
            runOnUiThread(() -> {
                feedbackAdapter = new FeedbackAdapter(ReviewFeedbackActivity.this, evaluations);
                feedbackListView.setAdapter(feedbackAdapter); // ListViewにアダプターをセット
            });
        }).start();
    }

    public void onBackPressed(View view) {
        // アクティビティを終了して前の画面に戻る
        finish();
    }
}
