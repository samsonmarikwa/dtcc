package com.dtcc.retailer.service;

import com.dtcc.retailer.dto.CustomerPoints;
import com.dtcc.retailer.exception.CustomerNotFoundException;
import com.dtcc.retailer.exception.MonthlyPointsException;
import com.dtcc.retailer.model.Customer;
import com.dtcc.retailer.model.Transactions;
import com.dtcc.retailer.repository.CustomerRepo;
import com.dtcc.retailer.repository.TransactionsRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class RetailerServiceTest {
   @InjectMocks
   private RetailerService retailerService;
   
   @Mock
   private CustomerRepo customerRepo;
   
   @Mock
   private TransactionsRepo transactionsRepo;
   
   private AutoCloseable closeable;
   Customer customer;
   List<Transactions> transactions;
   
   @BeforeEach
   void setUp() {
      closeable = MockitoAnnotations.openMocks(this);
      customer = createMockCustomer();
      transactions = createMockTransactions();
   }
   
   @BeforeEach
   public void tearDown() throws Exception {
      if (closeable != null) {
         closeable.close();
      }
   }
   
   private Customer createMockCustomer() {
      return Customer.builder()
            .customerId(1L)
            .customerName("Joe Bloggs")
            .build();
   }
   
   private List<Transactions> createMockTransactions() {
      return List.of(
            Transactions.builder()
                  .id(1L)
                  .tdate(LocalDate.of(2024, 7, 10))
                  .description("Laptop")
                  .amount(BigDecimal.valueOf(120.00))
                  .customer(customer)
                  .build(),
            Transactions.builder()
                  .id(2L)
                  .tdate(LocalDate.of(2024, 7, 23))
                  .description("Monitor")
                  .amount(BigDecimal.valueOf(80.00))
                  .customer(customer)
                  .build(),
            Transactions.builder()
                  .id(3L)
                  .tdate(LocalDate.of(2024, 7, 15))
                  .description("Keyboard")
                  .amount(BigDecimal.valueOf(20.00))
                  .customer(customer)
                  .build(),
            Transactions.builder()
                  .id(4L)
                  .tdate(LocalDate.of(2024, 8, 15))
                  .description("Camera")
                  .amount(BigDecimal.valueOf(60.00))
                  .customer(customer)
                  .build()
      );
   }
   
   @Test
   public void testGetCustomerPoints_CustomerFound() {
      when(customerRepo.findById(anyLong())).thenReturn(Optional.of(customer));
      when(transactionsRepo.findByCustomer(any(Customer.class))).thenReturn(transactions);
      
      CustomerPoints customerPoints = retailerService.getCustomerPoints(1L);
      
      assertNotNull(customerPoints, "CustomerPoints object should not be null");
      assertEquals("Joe Bloggs", customerPoints.getCustomerName(), "Customer name should be Joe Bloggs");
      assertEquals(2, customerPoints.getMonthlyPoints().size(), "Months with points should be 2");
      assertEquals(4, customerPoints.getTotalPoints(), "Total points should be 4");
   }
   
   @Test
   public void testGetCustomerPoints_CustomerNotFound() {
      when(customerRepo.findById(anyLong())).thenReturn(Optional.empty());
      when(transactionsRepo.findByCustomer(any(Customer.class))).thenReturn(new ArrayList<>());
      
      CustomerNotFoundException exception = assertThrows(CustomerNotFoundException.class,
            () -> retailerService.getCustomerPoints(1L));
      assertEquals("Customer Not found", exception.getMessage(), "Exception message should be 'Customer Not found'");
   }
   
   @Test
   public void testGetCustomerPoints_CustomerWithNoPoints() {
      when(customerRepo.findById(anyLong())).thenReturn(Optional.of(customer));
      when(transactionsRepo.findByCustomer(any(Customer.class))).thenReturn(new ArrayList<>());
      
      MonthlyPointsException exception = assertThrows(MonthlyPointsException.class,
            () -> retailerService.getCustomerPoints(1L));
      assertEquals("No monthly points earned", exception.getMessage(), "Exception message should be 'No monthly points earned'");
   }
   
}
