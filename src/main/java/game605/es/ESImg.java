package game605.es;

import co.elastic.clients.elasticsearch.license.LicenseStatus;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

/**
 * @author Koyou
 * @version 1.0.0
 * @className es img详细信息
 * @description es中Img索引
 * @since 2024/5/22 18:10
 */
@Data
@Document(indexName = "es_img",createIndex = true)
public class ESImg {

    @Id
    @Field(type = FieldType.Integer)
    private Integer id;
    @Field(type = FieldType.Text)
    private String path;
    @Field(type = FieldType.Keyword)
    private List<String> tags;

}
