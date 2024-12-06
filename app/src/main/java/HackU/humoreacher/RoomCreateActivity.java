package HackU.humoreacher;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import HackU.humoreacher.database.AppDatabase;
import HackU.humoreacher.entities.User;

public class RoomCreateActivity extends AppCompatActivity {

    private EditText roomIdEditText;
    private EditText roomPasswordEditText;
    private EditText adminPasswordEditText;
    private Button createRoomButton;

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_create);

        // XMLからビューを取得
        roomIdEditText = findViewById(R.id.roomIdEditText);
        roomPasswordEditText = findViewById(R.id.roomPasswordEditText);
        adminPasswordEditText = findViewById(R.id.adminPasswordEditText);
        createRoomButton = findViewById(R.id.createRoomButton);

        appDatabase = AppDatabase.getInstance(this);

        // 入力内容が変更されるたびにボタンをチェック
        roomIdEditText.addTextChangedListener(textWatcher);
        roomPasswordEditText.addTextChangedListener(textWatcher);
        adminPasswordEditText.addTextChangedListener(textWatcher);
    }

    // 入力内容が変更されたときに呼ばれるTextWatcher
    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            checkInputFields(); // 入力を確認
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    };

    // 入力内容を確認して、ボタンの有効/無効を切り替える
    private void checkInputFields() {
        String roomId = roomIdEditText.getText().toString().trim();
        String roomPassword = roomPasswordEditText.getText().toString().trim();
        String adminPassword = adminPasswordEditText.getText().toString().trim();

        // ルームID、ルームパスワード、管理者パスワードがすべて入力されていればボタンを有効化
        createRoomButton.setEnabled(!roomId.isEmpty() && !roomPassword.isEmpty() && !adminPassword.isEmpty());
    }

    // ルーム作成ボタンが押されたときの処理
    public void onCreateRoom(View view) {
        String roomId = roomIdEditText.getText().toString();
        String roomPassword = roomPasswordEditText.getText().toString();
        String adminPassword = adminPasswordEditText.getText().toString();

        // パスワードと管理者パスワードが同じかチェック
        if (roomPassword.equals(adminPassword)) {
            Toast.makeText(this, "パスワードと管理者パスワードは異なる必要があります", Toast.LENGTH_SHORT).show();
            return; // ルーム作成を中止
        }

// UserDaoを使って、ルームIDの重複チェック
        new Thread(() -> {
            int count = appDatabase.userDao().isUserExists(roomId);
            runOnUiThread(() -> {
                if (count > 0) { // COUNT(*) の結果が1以上なら既に存在
                    Toast.makeText(RoomCreateActivity.this, "既にログインIDは使用されています", Toast.LENGTH_SHORT).show();
                } else {
                    // 重複がなければユーザー（ルーム）を作成
                    User user = new User(roomId, roomPassword, adminPassword);
                    new Thread(() -> {
                        appDatabase.userDao().insert(user);
                        runOnUiThread(() -> {
                            Toast.makeText(RoomCreateActivity.this, "ルームが作成されました", Toast.LENGTH_SHORT).show();
                            // 次の画面に遷移
                            startActivity(new Intent(RoomCreateActivity.this, MainActivity.class));
                            finish();
                        });
                    }).start();
                }
            });
        }).start();


    }
}
