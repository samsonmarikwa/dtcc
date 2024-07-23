package com.dtcc.retailer.dto;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@Data
@ToString
public class CustomerPoints {
   private String customerName;
   private List<MonthlyPoints> monthlyPoints;
   private int totalPoints;
}
