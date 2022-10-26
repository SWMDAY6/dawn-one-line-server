package day6.dawnoneline.exception;

import java.util.NoSuchElementException;

import javax.persistence.NoResultException;

import org.hibernate.PropertyValueException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NoResultException.class)
    public String handleNoResultException(final NoResultException e) {
        return "Result does not exist!";
    }

    @ExceptionHandler(PropertyValueException.class)
    public String handlePropertyValueException(final PropertyValueException e) {
        return "not-null property references a null or transient value!";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMissingServletRequestParameterException(final MissingServletRequestParameterException e) {
        return "Missing Request Parameter!";
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException e) {
        return "Please check the url again!";
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String handleBadCredentialsException(final BadCredentialsException e) {
        return "The password is wrong!";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String handleNoSuchElementException(final NoSuchElementException e) {
        return "No Such Element!";
    }
}
