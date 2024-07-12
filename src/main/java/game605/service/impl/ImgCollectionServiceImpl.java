package game605.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import game605.bean.ImgCollection;
import game605.bean.ImgCollectionDetail;
import game605.bean.dto.ImgCollectionDTO;
import game605.bean.vo.ImgCollectionVO;
import game605.mapper.ImgCollectionDetailMapper;
import game605.service.ImgCollectionService;
import game605.mapper.ImgCollectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

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
        imgCollectionDTO.setImgCount(imgCollectionDTO.getImgList().size());
        ret += imgCollectionMapper.insert(imgCollectionDTO);
        // 设置子表内容
        for (ImgCollectionDetail imgCollectionDetail : imgCollectionDTO.getImgList()) {
            imgCollectionDetail.setCollectionId(imgCollectionDTO.getId());
            imgCollectionDetail.setCreateTime(new Date());
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

    /**
     * 获取单个图集详细信息
     * @param id
     * @return
     */
    @Override
    public ImgCollectionVO getImgCollectionInfo(long id) {
        // 查询主表
        ImgCollection imgCollection = imgCollectionMapper.selectById(id);
        ImgCollectionVO imgCollectionVO = new ImgCollectionVO(imgCollection);
        // 查询子表
        List<ImgCollectionDetail> imgCollectionDetails = imgCollectionDetailMapper
                .selectList(new QueryWrapper<ImgCollectionDetail>().eq("collection_id", id));
        imgCollectionVO.setImgList(imgCollectionDetails);
        return imgCollectionVO;
    }

    /**
     *
     * 查询图集列表
     *
     * @param query 查询条件
     * @param pageNum 分页页码
     * @param pageSize 分页大小
     * @return 图集列表
     */
    @Override
    public Page<ImgCollection> getImgCollectionList(ImgCollection query, int pageNum, int pageSize) {
        QueryWrapper<ImgCollection> wrapper = new QueryWrapper<>(query);
        return imgCollectionMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
    }


}




