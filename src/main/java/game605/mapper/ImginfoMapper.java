package game605.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import game605.bean.Imginfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Mapper
public interface ImginfoMapper  extends BaseMapper<Imginfo> {

    void insertImginfoSeq();

    Integer getNextImginfoId();

    List<Imginfo> getImginfoByIds(List<Integer> ids);

}
