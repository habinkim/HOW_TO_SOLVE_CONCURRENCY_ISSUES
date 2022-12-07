package com.example.stock.repository;

import com.example.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import static javax.persistence.LockModeType.OPTIMISTIC;
import static javax.persistence.LockModeType.PESSIMISTIC_WRITE;

public interface StockRepository extends JpaRepository<Stock, Long> {

	@Lock(PESSIMISTIC_WRITE)
	@Query("select s from Stock s where s.id = :id")
	Stock findByIdWithPessimisticLock(@Param("id") Long id);

	@Lock(OPTIMISTIC)
	@Query("select s from Stock s where s.id = :id")
	Stock findByIdWithOptimisticLock(@Param("id") Long id);

}