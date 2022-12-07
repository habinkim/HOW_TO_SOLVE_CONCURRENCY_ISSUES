package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.transaction.annotation.Propagation.REQUIRES_NEW;

@Service
@RequiredArgsConstructor
public class StockService {

	private final StockRepository stockRepository;

	@Transactional(propagation = REQUIRES_NEW)
	public synchronized void decrease(Long id, Long quantity) {
		// get stock
		Stock stock = stockRepository.findById(id).orElseThrow();

		stock.decrease(quantity);
		stockRepository.saveAndFlush(stock);
	}

}
