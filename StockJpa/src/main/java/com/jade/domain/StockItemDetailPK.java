package com.jade.domain;

import java.io.Serializable;

public class StockItemDetailPK implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String stockNo;			// 종목코드
	private String stockDate;		// 날짜
	
	public String getStockNo() {
		return stockNo;
	}
	public void setStockNo(String stockNo) {
		this.stockNo = stockNo;
	}
	public String getStockDate() {
		return stockDate;
	}
	public void setStockDate(String stockDate) {
		this.stockDate = stockDate;
	}
	
	
}
