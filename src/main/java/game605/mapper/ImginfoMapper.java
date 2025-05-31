package game605.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import game605.bean.Imginfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


@Repository
@Mapper
public interface ImginfoMapper  extends BaseMapper<Imginfo> {

    void insertImginfoSeq();

    Integer getNextImginfoId();

}
