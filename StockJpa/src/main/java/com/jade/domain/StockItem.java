package com.jade.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class StockItem {

	@Id
	private String stockNo;	// 종목코드
	
	private String stockNm;	// 주식회사명
	private int stockRank;	// 코스피 순위
	
	@OneToMany(mappedBy = "stockItem")
	private List<StockItemDetail> stockItemDetail = new ArrayList<StockItemDetail>();
	
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getStockNm() {
		return stockNm;
	}
	public void setStockNm(String stockNm) {
		this.stockNm = stockNm;
	}
	public int getStockRank() {
		return stockRank;
	}
	public void setStockRank(int stockRank) {
		this.stockRank = stockRank;
	}
	public List<StockItemDetail> getStockItemDetail() {
		return stockItemDetail;
	}
	public void setStockItemDetail(List<StockItemDetail> stockItemDetail) {
		this.stockItemDetail = stockItemDetail;
	}
}
