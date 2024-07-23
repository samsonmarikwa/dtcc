package com.dtcc.retailer.controller;

import com.dtcc.retailer.dto.CustomerPoints;
import com.dtcc.retailer.service.RetailerService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/retailer")
@AllArgsConstructor
public class RetailerController {
   private final RetailerService retailerService;
   
   @GetMapping("/pointsearned/{customerId}")
   public ResponseEntity<CustomerPoints> getPointsEarned(@PathVariable Long customerId) {
      CustomerPoints customerPoints = retailerService.getCustomerPoints(customerId);
      return ResponseEntity.ok(customerPoints);
   }
}
