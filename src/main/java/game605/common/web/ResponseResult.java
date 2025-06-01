package game605.common.web;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


/**
 * @author Koyou
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private Object data;

    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(Integer code, Object data) {
        this.code = code;
        this.data = data;
    }

    public ResponseResult(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseResult success(Object o) {
        return new ResponseResult(200, "success", o);
    }

    public static ResponseResult success(int code)  {
        return new ResponseResult(200, "success", code);
    }

    public static ResponseResult error(Object data) {
        return new ResponseResult(500, "error", data);
    }

    public static ResponseResult error(int code)  {
        return new ResponseResult(500, "error", code);
    }


    public static ResponseResult error(Object data, String msg)  {
        return new ResponseResult(500, msg, data);
    }


    public static ResponseResult result(boolean ret) {
        if(ret){
            return new ResponseResult(200, "success");
        }else {
            return new ResponseResult(500, "error");
        }
    }

}
