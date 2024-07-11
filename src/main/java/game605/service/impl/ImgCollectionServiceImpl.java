package game605.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import game605.bean.ImgCollection;
import game605.bean.ImgCollectionDetail;
import game605.bean.dto.ImgCollectionDTO;
import game605.mapper.ImgCollectionDetailMapper;
import game605.service.ImgCollectionService;
import game605.mapper.ImgCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
* @author Koyou
* @description 针对表【img_collection(画集)】的数据库操作Service实现
* @createDate 2024-07-10 15:49:17
*/
@Service
public class ImgCollectionServiceImpl extends ServiceImpl<ImgCollectionMapper, ImgCollection>
    implements ImgCollectionService{

    @Autowired
    private ImgCollectionMapper imgCollectionMapper;
    @Autowired
    private ImgCollectionDetailMapper imgCollectionDetailMapper;
    @Autowired
    private ImgCollectionDetailServiceImpl imgCollectionDetailService;


    /**
     * 添加画集
     * @param imgCollectionDTO 图集DTO
     * @return
     */
    @Override
    @Transactional
    public int addImgCollection(ImgCollectionDTO imgCollectionDTO) {
        int ret = 0;
        // 先插入主表
        imgCollectionDTO.setCreateTime(new Date());
        ret += imgCollectionMapper.insert(imgCollectionDTO);
        // 设置子表内容
        for (ImgCollectionDetail imgCollectionDetail : imgCollectionDTO.getImgList()) {
            imgCollectionDetail.setCollectionId(imgCollectionDTO.getId());
        }
        // 插入子表
        imgCollectionDetailService.saveBatch(imgCollectionDTO.getImgList());
        return ret;
    }


    /**
     * 删除画集
     * @param id 图集ID
     * @return
     */
    @Override
    @Transactional
    public int deleteImgCollection(long id) {
        // 删除主表
        int ret = imgCollectionMapper.deleteById(id);
        // 删除子表
        ret += imgCollectionDetailMapper.delete(new QueryWrapper<ImgCollectionDetail>().eq("collection_id", id));
        return ret;
    }

    @Override
    public int getImgCollectionInfo(long id) {
        return 0;
    }

}




