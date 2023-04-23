package game605.bean;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tag")
public class Tag implements Comparable<Tag>{

    @TableId
    private int id;
    private String name;
    private String message;
    private String cnName;
    private String clazz;
    private int imgCount;

    // 重构比较器
    @Override
    public int compareTo(Tag tag) {
        return Integer.compare(this.getImgCount(), tag.getImgCount()); // 逆序 compare(tag.getImgCount(), this.getImgCount());
    }
}
