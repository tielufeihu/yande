package game605.bean;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * 图片标签关联类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("img_tag")
public class ImgTag
{
    private Integer imgId;
    private Integer tagId;

}
