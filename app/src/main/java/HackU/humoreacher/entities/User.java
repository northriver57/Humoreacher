package HackU.humoreacher.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "users")  // テーブル名は 'users'
public class User {

    @PrimaryKey
    @NonNull
    private String id;  // ユーザーID
    private String password; // パスワード
    private String adminPassword; // 管理者パスワード

    // コンストラクタ、ゲッター、セッターを追加
    public User(String id, String password, String adminPassword) {
        this.id = id;
        this.password = password;
        this.adminPassword = adminPassword;
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
