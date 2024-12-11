package HackU.humoreacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.entities.Theme;

public class PrincipalDashboardActivity extends AppCompatActivity {

    private ListView rankingListView;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_dashboard);

        appDatabase = AppDatabase.getInstance(this);
        rankingListView = findViewById(R.id.rankingListView);
        RatingBar averageRatingBar = findViewById(R.id.averageRatingBar); // 平均評価用RatingBar


        // 平均評価を取得
        new Thread(() -> {
            float averageRating = appDatabase.evaluationDao().getAverageRating(); // 評価の平均を取得
            runOnUiThread(() -> {
                averageRatingBar.setRating(averageRating); // RatingBarに反映
            });
        }).start();

        // テーマのランキングを表示
        new Thread(() -> {
            List<Theme> themes = appDatabase.themeDao().getAllThemesOrderedBySelectionCount();
            runOnUiThread(() -> {
                ArrayAdapter<Theme> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, themes);
                rankingListView.setAdapter(adapter);
            });
        }).start();

        // 生徒の感想を確認ボタンのクリックイベント
        findViewById(R.id.viewFeedbackButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToReviewFeedback(v);  // goToReviewFeedback メソッドを呼び出し
            }
        });
    }

    public void goToReviewFeedback(View view) {
        // ReviewFeedbackActivity に遷移
        Intent intent = new Intent(this, ReviewFeedbackActivity.class);
        startActivity(intent);
    }
    public void onLogout(View view) {
        Intent intent = new Intent(this, LoginActivity.class); // LoginActivityは適宜変更してください
        startActivity(intent);
        finish(); // 現在のアクティビティを終了
    }
}
