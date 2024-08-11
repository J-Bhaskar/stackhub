package com.example.demo.repositrys;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.CompletedOrderTable;

public interface CompletedTableRepositry extends JpaRepository<CompletedOrderTable, Long> {

}
