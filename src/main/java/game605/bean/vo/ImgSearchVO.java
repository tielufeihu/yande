package game605.bean.vo;

import lombok.Data;

import java.util.List;

/**
 * @author koyou
 * @version 1.0.0
 * @className ImgSearchVo
 * @description 图片搜索VO
 * @since 2025/5/31 19:18
 */
@Data
public class ImgSearchVO {

    /**
     * {
     *     "tags": ["tag1","tag2","tag3","tag4","tag5","tag6","tag7","tags8"],
     *     "page":[1,100],
     *     "teen": [true or false]
     *  }
     */

    private List<Integer> tagIds;
    private boolean teenMode;
    private int pageNum;
    private int pageSize;

}
