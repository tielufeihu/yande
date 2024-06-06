package game605.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import game605.bean.ImgTag;
import game605.bean.vo.ImgTagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ImgTagMapper  extends BaseMapper<ImgTag> {

    List<ImgTagVO> getImgTagVOByImgId(@Param("imgId") Integer imgId);

}
