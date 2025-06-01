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
     * 图片标签IDs
     */
    private List<Integer> tagIds;
    /**
     * 是否开启青少年模式
     */
    private boolean teenMode;
    /**
     * 当前页码
     */
    private int pageNum;
    /**
     * 每页显示数量
     */
    private int pageSize;

}
