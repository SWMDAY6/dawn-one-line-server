package day6.dawnoneline.exception;

import javax.persistence.NoResultException;

import org.hibernate.PropertyValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResultException.class)
    public String handleNoResultException(final NoResultException e) {
        return "Input Id does not exist";
    }

    @ExceptionHandler(PropertyValueException.class)
    public String handlePropertyValueException(final PropertyValueException e) {
        return "not-null property references a null or transient value";
    }
}