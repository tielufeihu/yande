package game605.es;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collection;
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
//        client.search(searchReq -> searchReq.index("es_img")
//                .query(query -> {
//                    query.bool(bool -> bool.must())
//                }),ESImg.class);
        return null;
    }


}
