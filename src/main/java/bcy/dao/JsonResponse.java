package bcy.dao;

public class JsonResponse<T> {
    private Integer code;
    private String msg;
    private T data;

    public JsonResponse(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public JsonResponse(T data) {
        this.data = data;
        msg = "成功啦";
        code = 0;
    }

    public static JsonResponse<String> success() {
        return new JsonResponse<>(null);
    }

    public static JsonResponse<String> success(String data) {
        return new JsonResponse<>(data);
    }

    public static JsonResponse<String> failed() {
        return new JsonResponse<>(1, "失败了");
    }

    public static JsonResponse<String> failed(Integer code, String msg) {
        return new JsonResponse<>(code, msg);
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }
}
