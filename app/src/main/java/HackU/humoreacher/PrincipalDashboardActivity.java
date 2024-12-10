package HackU.humoreacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;

import androidx.appcompat.app.AppCompatActivity;

import HackU.humoreacher.database.AppDatabase;

public class PrincipalDashboardActivity extends AppCompatActivity {

    private RatingBar averageRatingBar;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_dashboard);

        // RatingBarの初期化
        averageRatingBar = findViewById(R.id.averageRatingBar);

        // AppDatabaseのインスタンス取得
        appDatabase = AppDatabase.getInstance(this);

        // RatingBarをタップ不可に設定
        averageRatingBar.setIsIndicator(true); // ユーザーが評価を変更できないようにする

        // データベースから評価を取得し、RatingBarに設定
        loadAverageRating();
    }

    // 平均評価をデータベースから取得して表示
    private void loadAverageRating() {
        new Thread(() -> {
            // ここで平均評価をデータベースから取得
            float averageRating = appDatabase.evaluationDao().getAverageRating();

            // UIの更新はメインスレッドで行う
            runOnUiThread(() -> {
                averageRatingBar.setRating(averageRating); // RatingBarに設定
            });
        }).start();
    }

    // ログアウト処理
    public void onLogout(View view) {
        // 例えば、ログイン画面に遷移させるなど
        Intent intent = new Intent(this, LoginActivity.class); // LoginActivityに遷移
        startActivity(intent);
        finish(); // このアクティビティを終了
    }

}
