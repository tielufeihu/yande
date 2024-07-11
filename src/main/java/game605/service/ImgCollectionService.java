package game605.service;

import game605.bean.ImgCollection;
import com.baomidou.mybatisplus.extension.service.IService;
import game605.bean.dto.ImgCollectionDTO;

/**
* @author Koyou
* @description 针对表【img_collection(画集)】的数据库操作Service
* @createDate 2024-07-10 15:49:17
*/
public interface ImgCollectionService extends IService<ImgCollection> {

    /**
     * 新增图集
     * @param imgCollectionDTO 图集DTO
     * @return
     */
    int addImgCollection(ImgCollectionDTO imgCollectionDTO);

    /**
     * 删除图集
     * @param id
     * @return
     */
    int deleteImgCollection(long id);

    int getImgCollectionInfo(long id);
}
