package HackU.humoreacher.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import HackU.humoreacher.entities.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    // ユーザーIDがすでに存在するかをチェックするメソッド
    @Query("SELECT COUNT(*) FROM users WHERE id = :roomId")
    int isUserExists(String roomId);

    // ユーザーIDとパスワードでユーザーを取得
    @Query("SELECT * FROM users WHERE id = :userId AND password = :password LIMIT 1")
    User getUserByIdAndPassword(String userId, String password);

    // 管理者IDとパスワードで管理者を取得
    @Query("SELECT * FROM users WHERE id = :userId AND adminPassword = :adminPassword LIMIT 1")
    User getUserByIdAndAdminPassword(String userId, String adminPassword);
}
