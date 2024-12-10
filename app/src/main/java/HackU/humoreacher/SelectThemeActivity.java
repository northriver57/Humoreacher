package HackU.humoreacher;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.entities.Theme;

public class SelectThemeActivity extends AppCompatActivity {

    private ListView themeListView;
    private String[] themes = {"テーマ1", "テーマ2", "テーマ3", "テーマ4", "テーマ5"};
    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);

        // AppDatabaseのインスタンス取得
        appDatabase = AppDatabase.getInstance(this);

        // テーマリストを表示するListView
        themeListView = findViewById(R.id.themeListView);

        // テーマをリストに表示
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, themes);
        themeListView.setAdapter(adapter);

        // テーマが選ばれたときの処理
        themeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // 選ばれたテーマの名前を取得
                String selectedTheme = themes[position];

                // データベースに選択回数をインクリメント
                new Thread(() -> {
                    appDatabase.themeDao().incrementSelectionCount(selectedTheme);
                    runOnUiThread(() -> {
                        // トーストで選択したテーマを表示
                        Toast.makeText(SelectThemeActivity.this, selectedTheme + " を選択しました", Toast.LENGTH_SHORT).show();
                    });
                }).start();
            }
        });
    }
    public void onBackButtonClicked(View view) {
        onBackPressed(); // 標準的な戻る処理
    }
}
