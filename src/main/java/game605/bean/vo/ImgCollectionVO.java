package game605.bean.vo;

import game605.bean.ImgCollection;
import game605.bean.ImgCollectionDetail;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ImgCollectionVO
 * @description 画集显示VO
 * @since 2024/7/10 17:51
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ImgCollectionVO extends ImgCollection {

    private List<ImgCollectionDetail> imgList;

    public ImgCollectionVO(){}

    public ImgCollectionVO(ImgCollection imgCollection){
        this.setId(imgCollection.getId());
        this.setName(imgCollection.getName());
        this.setCnName(imgCollection.getCnName());
        this.setImgCount(imgCollection.getImgCount());
        this.setAuthor(imgCollection.getAuthor());
        this.setDescription(imgCollection.getDescription());
        this.setCover(imgCollection.getCover());
        this.setCreateTime(imgCollection.getCreateTime());
    }

}
