package barbershop_api.barbershop.Exceptions;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>() {{
        add("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE);
    }};

    @ExceptionHandler(DefaultExceptionHandler.class)
    public final ResponseEntity<Object> handleAllExceptions(DefaultExceptionHandler ex, WebRequest request) {
        ErrorResponse error = ex.getErrorResponse();
        return new ResponseEntity(error, headers, HttpStatus.valueOf(error.getStatusCode()));
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorResponse error = ErrorResponse
                .builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .userMessage("Operação inválida! Ocorreu um erro ao executar a operação.")
                .developerMessage(ExceptionUtils.getStackTrace(ex))
                .details(new ArrayList<>())
                .build();
        return new ResponseEntity(error, headers, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> details = Optional.ofNullable(ex.getBindingResult().getFieldErrors())
                .orElseGet(Collections::emptyList)
                .stream()
                .map(ObjectError::getDefaultMessage)
                .collect(Collectors.toList());

        ErrorResponse error = ErrorResponse
                .builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .userMessage("Operação inválida! Existem campos obrigatórios não preenchidos.")
                .developerMessage(ExceptionUtils.getStackTrace(ex))
                .details(details)
                .build();

        return new ResponseEntity(error, this.headers, HttpStatus.BAD_REQUEST);
    }
}
