package HackU.humoreacher;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SelectThemeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_theme);
    }

    public void onSelectTheme(View view) {
        // テーマ選択時の処理（データベース保存等）
        Toast.makeText(this, "テーマが選ばれました", Toast.LENGTH_SHORT).show();
        finish(); // 選択後に画面を閉じる
    }
}
