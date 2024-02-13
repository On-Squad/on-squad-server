package onsquad.onsquadserver.global.error;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import onsquad.onsquadserver.global.common.response.Response;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Response<Map<String, String>> handlingConstraintViolationException(
            ConstraintViolationException e
    ) {
        Map<String, String> errors = e.getConstraintViolations().stream()
                .collect(Collectors.toMap(violation ->
                                StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                                        .reduce((first, second) -> second)
                                        .get().toString(),
                        ConstraintViolation::getMessage
                ));
        return Response.fail(errors);
    }
}
