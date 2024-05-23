package game605.es;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author Koyou
 * @version 1.0.0
 * @className es img详细信息
 * @description TODO
 * @since 2024/5/22 18:10
 */
@Data
@Document(indexName = "img",createIndex = true)
public class Img {

    @Id
    @Field(type = FieldType.Integer)
    private Integer id;
    @Field(type = FieldType.Text)
    private String path;
    @Field(type = FieldType.Binary)
    private byte[] img;
    @Field(analyzer="ik_max_word", type = FieldType.Text)
    private String tags;

}
