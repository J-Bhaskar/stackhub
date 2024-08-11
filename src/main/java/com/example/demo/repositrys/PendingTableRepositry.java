package com.example.demo.repositrys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.PendingOrderTable;


public interface PendingTableRepositry extends JpaRepository<PendingOrderTable, Long> {

	List<PendingOrderTable>findBySellerPriceLessThanEqualOrderBySellerPriceAsc(double price);

}
