package HackU.humoreacher.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import HackU.humoreacher.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    // 'userId' ではなく、'id' を使う
    @Query("SELECT * FROM users WHERE id = :userId AND password = :password LIMIT 1")  // 修正
    User getUserByIdAndPassword(String userId, String password);

    @Query("SELECT * FROM users")  // 'users' テーブルから全ユーザーを取得
    List<User> getAllUsers();

    // ユーザーIDがすでに存在するかをチェックするメソッド
    @Query("SELECT COUNT(*) FROM users WHERE id = :roomId")
    int isUserExists(String roomId);
}
