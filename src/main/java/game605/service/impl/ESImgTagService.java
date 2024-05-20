package game605.service.impl;

import game605.bean.Tag;
import game605.service.IImgTagService;

import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ESImgTagService
 * @description 图片tag搜素 -- ES实现
 * @since 2024/5/19 16:38
 */
public class ESImgTagService implements IImgTagService {

    @Override
    public List<Tag> getImgTagsFromId(int imgId) {
        return null;
    }

    @Override
    public List<Integer> getImgsIdFromTag(int tagId) {
        return null;
    }

    @Override
    public List<Integer> getImgsIdFromTag(int tagId, int page, int sept) {
        return null;
    }

    @Override
    public List<Integer> getImgsIdFromTags(String[] tags, int page, int sept) {
        return null;
    }

    @Override
    public int addTagToImg(int imgId, int tagId) {
        return 0;
    }

}
