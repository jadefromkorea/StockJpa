package jpabook.jpashop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class StockItemDetail {

	@Id @GeneratedValue
    @Column(name = "STOCK_DETAIL_NO")
    private Long no;
	
//	@ManyToOne
//	@JoinColumn(name = "stockNo")
	private String stockNo;	// 종목코드
	private String stockDate;		// 날짜
	private int openingPrice;	// 시가
	private int closingPrice;	// 종가
	private int highPrice;		// 고가
	private int lowPrice;		// 저가
	private int stockVolume;		// 거래량
	private String netChangeSign;		// 전일대비 상승, 하락
	private int netChange;		// 전일대비 종가
	
//	private String curTime;		// 현재시간
//	private int cutPrice;		// 현재가
//	private int compPrice;		// 전일대비
//	private float highRowRatio;	// 등락률
	
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
	public int getOpeningPrice() {
		return openingPrice;
	}
	public void setOpeningPrice(int openingPrice) {
		this.openingPrice = openingPrice;
	}
	public int getClosingPrice() {
		return closingPrice;
	}
	public void setClosingPrice(int closingPrice) {
		this.closingPrice = closingPrice;
	}
	public int getHighPrice() {
		return highPrice;
	}
	public void setHighPrice(int highPrice) {
		this.highPrice = highPrice;
	}
	public int getLowPrice() {
		return lowPrice;
	}
	public void setLowPrice(int lowPrice) {
		this.lowPrice = lowPrice;
	}
	public int getStockVolume() {
		return stockVolume;
	}
	public void setStockVolume(int stockVolume) {
		this.stockVolume = stockVolume;
	}
	public String getNetChangeSign() {
		return netChangeSign;
	}
	public void setNetChangeSign(String netChangeSign) {
		this.netChangeSign = netChangeSign;
	}
	public int getNetChange() {
		return netChange;
	}
	public void setNetChange(int netChange) {
		this.netChange = netChange;
	}
}
