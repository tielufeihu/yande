package game605.common.web;

import lombok.Data;

/**
 * @author sunst
 * @version 1.0.0
 * @className BaseEntity
 * @description TODO
 * @since 2025/6/1 20:16
 */
@Data
public class BaseEntity {

    private String createDate;
    private String updateDate;
    private String createdBy;
    private String updatedBy;
    private String remark;

}
