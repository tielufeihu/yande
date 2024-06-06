package game605.es;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ESImgRepository
 * @description Img es 连接类
 * @since 2024/6/6 13:43
 */
public interface ESImgRepository extends ElasticsearchRepository<ESImg, String> {

    @Query("""
            {
              "query": {
                "terms": {
                  "tag": #{#tags}
                }
              }
            }
           """)
    Page<Integer> getImgsIdFromTags(Collection<String> tags, Pageable pageable);

}
