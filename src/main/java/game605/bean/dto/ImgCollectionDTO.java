package game605.bean.dto;

import game605.bean.ImgCollection;
import game605.bean.ImgCollectionDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ImgCollectionDTO
 * @description 画集新增DTO
 * @since 2024/7/10 17:52
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ImgCollectionDTO extends ImgCollection {

    /**
     * 图片列表
     */
    private List<ImgCollectionDetail> imgList;

}
