package onsquad.onsquadserver.global.error;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("${server.error.path:${error.path:/error}}")
public class GlobalErrorHandler implements ErrorController {

    private static final String NOTFOUND = "요청하신 Endpoint 는 존재하지 않습니다.";
    private static final String ERROR = "애플리케이션에 예상치 못한 오류가 발생했습니다.";

    @RequestMapping
    public Object handleError(HttpServletRequest httpServletRequest) {
        RequestDispatcherResolver requestDispatcherResolver = new RequestDispatcherResolver(httpServletRequest);
        HttpStatus httpStatus = requestDispatcherResolver.resolveHttpStatus();
        String errorMessage = requestDispatcherResolver.resolveErrorMessage();
        String requestUri = requestDispatcherResolver.resolveRequestUri();
        String reasonPhrase = httpStatus.getReasonPhrase();
        return errorMessage;
    }
}
