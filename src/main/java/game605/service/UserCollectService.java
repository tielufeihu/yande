package game605.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import game605.bean.UserCollect;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author Koyou
* @description 针对表【user_collect(用户收藏)】的数据库操作Service
* @createDate 2024-07-10 15:56:45
*/
public interface UserCollectService extends IService<UserCollect> {

    /**
     * 收藏
     * @param userCollect
     * @return
     */
    int collect(UserCollect userCollect);

    /**
     * 取消收藏
     * @param userCollect
     * @return
     */
    int cancelCollect(UserCollect userCollect);

    /**
     * 查询收藏
     * @param userCollect
     * @return
     */
    Page<UserCollect> queryCollect(UserCollect userCollect);
}
