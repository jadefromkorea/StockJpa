package jpabook.jpashop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.StockItem;
import jpabook.jpashop.domain.StockItemDetail;
import jpabook.jpashop.repository.StockAnalRepository;

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
        return stockAnalRepository.findAll();
    }

    public StockItem findOne(String stockNo) {
        return stockAnalRepository.findOne(stockNo);
    }
}
