package game605.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("imginfo")
public class Imginfo {

    @TableId
    private Integer id;
    private String path;
    private byte[] img;

    /**
     * vo
     */
    private List<ImgTag> imgTags;

}
