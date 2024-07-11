package game605.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import game605.bean.ImgCollection;
import com.baomidou.mybatisplus.extension.service.IService;
import game605.bean.dto.ImgCollectionDTO;
import game605.bean.vo.ImgCollectionVO;

import java.util.List;

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

    /**
     * 获取单个图集详细信息
     * @param id
     * @return 图集详细信息
     */
    ImgCollectionVO getImgCollectionInfo(long id);


    /**
     *
     * 查询图集列表
     *
     * @param query 查询条件
     * @param pageNum 分页页码
     * @param pageSize 分页大小
     * @return 图集列表
     */
    Page<ImgCollection> getImgCollectionList(ImgCollection query, int pageNum, int pageSize);

}
