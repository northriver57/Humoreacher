package HackU.humoreacher.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import HackU.humoreacher.entities.User;

@Dao
public interface UserDao {

    @Insert
    void insert(User user);

    @Query("SELECT * FROM users WHERE id = :userId AND password = :password LIMIT 1")
    User getUserByIdAndPassword(String userId, String password);

    @Query("SELECT * FROM users WHERE id = :userId LIMIT 1")
    User getUserById(String userId);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();
}
