package com.logistics.cloud.response;


import com.logistics.cloud.eunm.ResultCode;
import lombok.Data;

@Data
public class JsonResponse<T> {

    /**
     * 1.status状态值：代表本次请求response的状态结果。
     */
    private String code;
    /**
     * 2.response描述：对本次状态码的描述。
     */
    private String message;
    /**
     * 3.data数据：本次返回的数据。
     */
    private T data;

    /**
     * 成功，创建ResResult：没data数据
     */
    public static JsonResponse success() {
        JsonResponse result = new JsonResponse();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 成功，创建ResResult：有data数据
     */
    public static JsonResponse success(Object data) {
        JsonResponse result = new JsonResponse();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 失败，指定status、desc
     */
    public static JsonResponse fail(String code, String desc) {
        JsonResponse result = new JsonResponse();
        result.setCode(code);
        result.setMessage(desc);
        return result;
    }

    /**
     * 失败，指定ResultCode枚举
     */
    public static JsonResponse fail(ResultCode resultCode) {
        JsonResponse result = new JsonResponse();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 把ResultCode枚举转换为ResResult
     */
    private void setResultCode(ResultCode code) {
        this.code = code.code();
        this.message = code.message();
    }
}
