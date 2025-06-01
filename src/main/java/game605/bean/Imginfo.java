package game605.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import game605.common.web.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 图片信息
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("imginfo")
public class Imginfo extends BaseEntity {

    @TableId
    private Integer id;
    private String path;
    private byte[] img;

    private Long size;
    private String title;
    private Integer authorId;
    private String authorName;
    private Integer width;
    private Integer height;
    private Integer hide;

    /**
     * vo
     */
    private List<ImgTag> imgTags;
    private String fullImgPath;

}
