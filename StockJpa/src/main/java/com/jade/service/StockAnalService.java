package com.jade.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jade.domain.StockItem;
import com.jade.domain.StockItemDetail;
import com.jade.repository.StockAnalRepository;

/**
 * User: HolyEyE
 * Date: 2013. 12. 3. Time: 오후 9:43
 */
@Service
@Transactional
public class StockAnalService {

    @Autowired
    StockAnalRepository stockAnalRepository;

    public void saveItemStock(StockItem stockItem) {
    	stockAnalRepository.save(stockItem);
    }
    public void saveItemStockDetail(StockItemDetail stockItemDetail) {
    	stockAnalRepository.saveDetail(stockItemDetail);
    }

    public List<StockItem> findItems() {
        return stockAnalRepository.findAllByOrderByStockRankDesc();
    }

    public StockItem findOne(String stockNo) {
        return stockAnalRepository.findOne(stockNo);
    }
    
    public String findOneMaxStockDateDetail() {
    	return stockAnalRepository.findOneMaxStockDateDetail();
    }
}
