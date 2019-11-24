package pl.niewolnyFilip.club_search.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import pl.niewolnyFilip.club_search.exception.MyException;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = MyException.class)
    public ResponseEntity<Object> exception(MyException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
