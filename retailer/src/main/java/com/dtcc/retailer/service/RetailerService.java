package com.dtcc.retailer.service;

import com.dtcc.retailer.dto.CustomerPoints;
import com.dtcc.retailer.dto.MonthlyPoints;
import com.dtcc.retailer.exception.CustomerNotFoundException;
import com.dtcc.retailer.exception.MonthlyPointsException;
import com.dtcc.retailer.model.Customer;
import com.dtcc.retailer.model.Transactions;
import com.dtcc.retailer.repository.CustomerRepo;
import com.dtcc.retailer.repository.TransactionsRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class RetailerService {
   private final CustomerRepo customerRepo;
   private final TransactionsRepo transactionsRepo;
   
   public CustomerPoints getCustomerPoints(Long customerId) {
      // Get customer
      Optional<Customer> optionalCustomer = customerRepo.findById(customerId);
      Customer customer = optionalCustomer
            .orElseThrow(() -> new CustomerNotFoundException("Customer Not found"));
      
      // Get transactions for customer
      List<Transactions> transactions = transactionsRepo.findByCustomer(customer);
      
      // Calculate monthly points
      Map<String, Integer> monthlyPoints = getMonthlyPoints(transactions);
      List<MonthlyPoints> monthlyPointsList = new ArrayList<>();
      monthlyPoints.forEach((month, points) -> {
         MonthlyPoints monthlyPoint = new MonthlyPoints();
         monthlyPoint.setMonth(month);
         monthlyPoint.setPoints(points);
         monthlyPointsList.add(monthlyPoint);
      });
      
      // Calculate total points
      int totalPoints = monthlyPoints.values()
            .stream().reduce(Integer::sum)
            .orElseThrow(() -> new MonthlyPointsException("No monthly points earned"));
      
      // Prepare and return CustomerPoints object
      CustomerPoints customerPoints = new CustomerPoints();
      customerPoints.setCustomerName(customer.getCustomerName());
      customerPoints.setMonthlyPoints(monthlyPointsList);
      customerPoints.setTotalPoints(totalPoints);
      return customerPoints;
   }
   
   private Map<String, Integer> getMonthlyPoints(List<Transactions> transactions) {
      Map<String, Integer> points = new HashMap<>();
      for (Transactions transaction : transactions) {
         double amount = transaction.getAmount().doubleValue();
         
         // Calculate points
         if (amount >= 50.00) {
            String yearMonth = transaction.getTdate().getYear()
                  + "-" + transaction.getTdate().getMonthValue();
            Integer pointsEarned = points.getOrDefault(yearMonth, 0);
            if (amount <= 100.00) {
               ++pointsEarned;
            } else {
               pointsEarned += 2;
            }
            points.put(yearMonth, pointsEarned);
         }
      }
      return points;
   }
}
