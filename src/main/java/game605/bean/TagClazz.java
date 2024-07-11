package game605.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 标签类型
 * @author Koyou
 * @TableName tag_clazz
 */
@TableName(value ="tag_clazz")
@Data
public class TagClazz implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Integer clazzId;

    /**
     * 类型名称
     */
    private String clazzName;

    /**
     * 等级
     */
    private Integer grade;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TagClazz other = (TagClazz) that;
        return (this.getClazzId() == null ? other.getClazzId() == null : this.getClazzId().equals(other.getClazzId()))
            && (this.getClazzName() == null ? other.getClazzName() == null : this.getClazzName().equals(other.getClazzName()))
            && (this.getGrade() == null ? other.getGrade() == null : this.getGrade().equals(other.getGrade()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getClazzId() == null) ? 0 : getClazzId().hashCode());
        result = prime * result + ((getClazzName() == null) ? 0 : getClazzName().hashCode());
        result = prime * result + ((getGrade() == null) ? 0 : getGrade().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", clazzId=").append(clazzId);
        sb.append(", clazzName=").append(clazzName);
        sb.append(", grade=").append(grade);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
