package com.jade.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.jade.domain.StockItem;
import com.jade.domain.StockItemDetail;

/**
 * User: HolyEyE
 * Date: 2013. 12. 3. Time: 오후 9:48
 */

@Repository
public class StockAnalRepository {

    @PersistenceContext
    EntityManager em;

    public void save(StockItem stockItem) {
        if (stockItem.getStockNo() == null) {
            em.persist(stockItem);
        } else {
            em.merge(stockItem);
        }
    }
    
    public void saveDetail(StockItemDetail stockItemDetail) {
    	if (stockItemDetail.getStockNo() == null) {
    		em.persist(stockItemDetail);
    	} else {
    		em.merge(stockItemDetail);
    	}
    }

    public StockItem findOne(String stockNo) {
        return em.find(StockItem.class, stockNo);
    }

    public List<StockItem> findAllByOrderByStockRankDesc() {
        return em.createQuery("select i from StockItem i", StockItem.class).getResultList();
    }
    
    public String findOneMaxStockDateDetail() {
    	return (String) em.createQuery("select max(s.stockDate) from StockItemDetail s").getSingleResult();
    }
}
