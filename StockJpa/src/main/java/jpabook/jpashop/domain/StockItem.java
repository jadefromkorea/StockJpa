package jpabook.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class StockItem {

	@Id
//	@OneToMany
//	@JoinColumn(name = "stockNo")
	private String stockNo;	// 종목코드
	
	private String stockNm;	// 주식회사명
	private int stockRank;	// 코스피 순위
	
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
}
