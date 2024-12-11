package HackU.humoreacher;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.entities.Theme;

public class SelectThemeActivity extends AppCompatActivity {

    private ListView themeListView;
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);

        appDatabase = AppDatabase.getInstance(this);
        themeListView = findViewById(R.id.themeListView);

        // テーマリストをデータベースから取得
        new Thread(() -> {
            List<Theme> themes = appDatabase.themeDao().getAllThemesOrderedBySelectionCount();
            runOnUiThread(() -> {
                ArrayAdapter<Theme> adapter = new ArrayAdapter<>(
                        this, android.R.layout.simple_list_item_1, themes);
                themeListView.setAdapter(adapter);
            });
        }).start();

        // テーマ選択時の処理
        themeListView.setOnItemClickListener((AdapterView<?> parent, View view, int position, long id) -> {
            Theme selectedTheme = (Theme) themeListView.getItemAtPosition(position);

            new Thread(() -> {
                appDatabase.themeDao().incrementSelectionCount(selectedTheme.name);
                runOnUiThread(() -> Toast.makeText(
                        SelectThemeActivity.this,
                        selectedTheme.name + " を選択しました",
                        Toast.LENGTH_SHORT).show());
            }).start();
        });
    }
    // 戻るボタンの処理
    public void onBackButtonClicked(View view) {
        onBackPressed();
    }
}

