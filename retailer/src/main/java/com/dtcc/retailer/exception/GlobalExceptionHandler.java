package com.dtcc.retailer.exception;

import com.dtcc.retailer.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
   
   @ExceptionHandler(CustomerNotFoundException.class)
   public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotFoundException ex) {
      return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NOT_FOUND);
   }
   
   @ExceptionHandler(MonthlyPointsException.class)
   public ResponseEntity<Object> handleMonthlyPointsException(MonthlyPointsException ex) {
      return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.NO_CONTENT);
   }
   
   @ExceptionHandler(Exception.class)
   public ResponseEntity<Object> handleException(Exception ex) {
      return new ResponseEntity<>(new ErrorResponse(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
