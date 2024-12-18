package HackU.humoreacher.entities;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "themes")
public class Theme {

    @PrimaryKey(autoGenerate = true)  // 自動生成されるID
    public int id;  // IDフィールド

    public String name;  // テーマ名
    public int selectionCount;  // 選択回数

    // 引数のあるコンストラクタ
    @Ignore
    public Theme(String name, int selectionCount) {
        this.name = name;
        this.selectionCount = selectionCount;
    }

    // デフォルトの引数なしコンストラクタ（Roomが使う）
    public Theme() {}

    @Override
    public String toString() {
        // nameとselectionCountを表示する例
        return name;
    }
}
