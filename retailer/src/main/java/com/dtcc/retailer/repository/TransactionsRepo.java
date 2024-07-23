package com.dtcc.retailer.repository;

import com.dtcc.retailer.model.Customer;
import com.dtcc.retailer.model.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepo extends JpaRepository<Transactions, Long> {
   List<Transactions> findByCustomer(Customer customer);
}
