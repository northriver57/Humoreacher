package HackU.humoreacher.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "themes")
public class Theme {

    @PrimaryKey(autoGenerate = true)
    public int id;  // 自動生成されるID

    public String name;  // テーマ名
    public int selectionCount;  // 選ばれた回数

    public Theme(String name) {
        this.name = name;
        this.selectionCount = 0;  // 初期選択回数は0
    }
}
