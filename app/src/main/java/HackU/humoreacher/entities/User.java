package HackU.humoreacher.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "users")
public class User {

    @PrimaryKey
    @NonNull
    private String id;  // ユーザーID
    private String password; // ユーザー用パスワード
    private String adminPassword; // 管理者用パスワード

    // コンストラクタ、ゲッター、セッター
    public User(String id, String password, String adminPassword) {
        this.id = id.trim();  // 空白を削除
        this.password = password.trim();  // 空白を削除
        this.adminPassword = adminPassword.trim();  // 空白を削除
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

    public void setAdminPassword(String adminPassword) {
        this.adminPassword = adminPassword;
    }
}
