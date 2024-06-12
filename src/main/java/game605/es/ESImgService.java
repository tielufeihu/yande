package game605.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className ESImgService
 * @description es调用service
 * @since 2024/6/6 14:30
 */
@Service
public class ESImgService {

    @Autowired
    private ESImgRepository esImgRepository;

    @Autowired
    private ElasticsearchClient client;



    Page<ESImg> getImgsIdFromTags(Collection<String> tags, Pageable pageable){
        return esImgRepository.getImgsIdFromTags(tags, pageable);
    }

    List<ESImg> getImgsIdFromTags(Collection<String> tags, int page, int step) throws IOException {
        // TODO
        Query query = new StringQuery("""
        "bool": {
            "must": [
                {
                    "bool": {
                        "must": [
                            {
                                "term": {
                                    "tags": "thighhighs"
                                }
                            },
                            {
                                "term": {
                                    "tags": "genshin_impact"
                                }
                            }
                        ]
                    }
                }
            ]
        }
                """);
        List<ESImg> ret = new ArrayList<>();
        return ret;
    }


}
