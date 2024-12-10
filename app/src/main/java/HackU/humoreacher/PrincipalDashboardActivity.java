package HackU.humoreacher;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.entities.Theme;

import java.util.List;

public class PrincipalDashboardActivity extends AppCompatActivity {

    private ListView themeRankingListView;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal_dashboard);

        // AppDatabaseのインスタンス取得
        appDatabase = AppDatabase.getInstance(this);

        // テーマランキングを表示するListView
        themeRankingListView = findViewById(R.id.themeRankingListView);

        // データベースからテーマを取得してランキングを表示
        loadThemeRankings();
    }

    private void loadThemeRankings() {
        new Thread(() -> {
            List<Theme> themes = appDatabase.themeDao().getAllThemesOrderedBySelectionCount();
            runOnUiThread(() -> {
                // テーマ名と選択数を表示するための配列を作成
                String[] themeRankings = new String[themes.size()];
                for (int i = 0; i < themes.size(); i++) {
                    themeRankings[i] = themes.get(i).name + " - " + themes.get(i).selectionCount + " 回";
                }

                // ListViewにランキングを表示
                ArrayAdapter<String> adapter = new ArrayAdapter<>(PrincipalDashboardActivity.this, android.R.layout.simple_list_item_1, themeRankings);
                themeRankingListView.setAdapter(adapter);
            });
        }).start();
    }

    // onViewFeedbackメソッドを追加してReviewFeedbackActivityに遷移
    public void onViewFeedback(View view) {
        Intent intent = new Intent(PrincipalDashboardActivity.this, ReviewFeedbackActivity.class);
        startActivity(intent);
    }
    public void onLogout(View view) {
        // ログイン画面に戻る処理（またはログアウト処理）
        Intent intent = new Intent(PrincipalDashboardActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
