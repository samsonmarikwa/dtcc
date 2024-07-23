package com.dtcc.retailer.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NO_CONTENT)
public class MonthlyPointsException extends RuntimeException {
   public MonthlyPointsException(String message) {
      super(message);
   }
}
