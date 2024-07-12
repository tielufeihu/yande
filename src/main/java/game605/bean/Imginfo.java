package game605.bean;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("imginfo")
public class Imginfo {

    @TableId
    private int id;
    private String path;
    private byte[] img;

    public void setNewIdAdd1(){
        String oldId = this.path;
        String newId = String.valueOf(Integer.parseInt(oldId)+1);
        this.path = newId;
    }

}
