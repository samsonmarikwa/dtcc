package com.dtcc.retailer.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
public class Customer {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long customerId;
   private String customerName;
   @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   private Set<Transactions> transactions;
}
