package game605.bean.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import game605.bean.ImgTag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ImgTagVO
 * @description 图片tagVO
 * @since 2024/6/6 17:57
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImgTagVO extends ImgTag {

    private String tagName;

}
