package game605.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import game605.bean.UserCollect;
import game605.service.UserCollectService;
import game605.mapper.UserCollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
* @author Koyou
* @description 针对表【user_collect(用户收藏)】的数据库操作Service实现
* @createDate 2024-07-10 15:56:45
*/
@Service
public class UserCollectServiceImpl extends ServiceImpl<UserCollectMapper, UserCollect>
    implements UserCollectService{

    @Autowired
    private UserCollectMapper userCollectMapper;

    @Override
    public int collect(UserCollect userCollect) {
        if(userCollectMapper.exists(new QueryWrapper<UserCollect>()
                .eq("user_id", userCollect.getUserId())
                .eq("img_id", userCollect.getImgId()))){
            throw new RuntimeException("该图片已收藏");
        }
        return userCollectMapper.insert(userCollect.setCollectDate(new Date()));
    }

    @Override
    public int cancelCollect(UserCollect userCollect) {
        return userCollectMapper.deleteById(userCollect.getId());
    }

    @Override
    public Page<UserCollect> queryCollect(Integer userId, Integer imgId, Integer pageNum, Integer pageSize) {
        return userCollectMapper
                .selectPage(new Page<>(pageNum, pageSize),
                        new QueryWrapper<UserCollect>()
                                .eq("user_id", userId)
                                .orderByDesc("collect_date"));
    }
}




