package onsquad.onsquadserver.global.common.response;

import lombok.Getter;

import java.time.LocalDateTime;

import static onsquad.onsquadserver.global.common.response.MessageStatus.*;

@Getter
public class Response<T> {

    private String message;
    private T data;
    private LocalDateTime responseAt;

    private Response(LocalDateTime responseAt, T data, String message) {
        this.responseAt = responseAt;
        this.data = data;
        this.message = message;
    }

    private Response(T data, String message) {
        this.responseAt = LocalDateTime.now();
        this.data = data;
        this.message = message;
    }

    public static <T> Response<T> success(T data) {
        return new Response<>(data, SUCCESS);
    }

    public static <T extends ResponseData> Response<T> success(T data) {
        return new Response<>(data, SUCCESS);
    }

    public static <T> Response<T> fail(T data) {
        return new Response<>(data, FAIL);
    }

    public static <T extends ResponseData> Response<T> fail(T data) {
        return new Response<>(data, FAIL);
    }
}
