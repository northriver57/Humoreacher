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

        roomIdEditText = findViewById(R.id.roomIdEditText);
        roomPasswordEditText = findViewById(R.id.roomPasswordEditText);
        adminPasswordEditText = findViewById(R.id.adminPasswordEditText);
        createRoomButton = findViewById(R.id.createRoomButton);

        appDatabase = AppDatabase.getInstance(this);

        // 入力欄の変更を監視し、ボタンの有効化/無効化を管理
        roomIdEditText.addTextChangedListener(textWatcher);
        roomPasswordEditText.addTextChangedListener(textWatcher);
        adminPasswordEditText.addTextChangedListener(textWatcher);
    }

    private final TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            checkInputFields();
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    private void checkInputFields() {
        String roomId = roomIdEditText.getText().toString().trim();
        String roomPassword = roomPasswordEditText.getText().toString().trim();
        String adminPassword = adminPasswordEditText.getText().toString().trim();
        createRoomButton.setEnabled(!roomId.isEmpty() && !roomPassword.isEmpty() && !adminPassword.isEmpty());
    }

    public void onCreateRoom(View view) {
        String roomId = roomIdEditText.getText().toString().trim();
        String roomPassword = roomPasswordEditText.getText().toString().trim();
        String adminPassword = adminPasswordEditText.getText().toString().trim();

        if (roomPassword.equals(adminPassword)) {
            Toast.makeText(this, "パスワードと管理者パスワードは異なる必要があります", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            int count = appDatabase.userDao().isUserExists(roomId);
            runOnUiThread(() -> {
                if (count > 0) {
                    Toast.makeText(RoomCreateActivity.this, "既にログインIDは使用されています", Toast.LENGTH_SHORT).show();
                } else {
                    User user = new User(roomId, roomPassword, adminPassword);
                    new Thread(() -> {
                        appDatabase.userDao().insert(user);
                        runOnUiThread(() -> {
                            Toast.makeText(RoomCreateActivity.this, "ルームが作成されました", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RoomCreateActivity.this, MainActivity.class);
                            startActivity(intent);
                        });
                    }).start();
                }
            });
        }).start();
    }

    // 戻るボタンのクリックイベント
    public void onBackButtonClicked(View view) {
        // 標準の戻る動作を呼び出す
        onBackPressed();
    }
}
