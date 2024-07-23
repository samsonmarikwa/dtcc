package com.dtcc.retailer.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Transactions {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;
   @JsonFormat(pattern = "MM/dd/yyyy")
   private LocalDate tdate;
   private String description;
   private BigDecimal amount;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "customer_id")
   private Customer customer;
}
