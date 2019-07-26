package com.jade.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jade.domain.StockItem;
import com.jade.domain.StockItemDetail;
import com.jade.service.StockAnalService;

@Controller
public class StockItemController {

	@Autowired
	private StockAnalService stockAnalService;

	@RequestMapping("/setListStockItemInfoList.do")
	public String setListStockItemInfoList(Model model) throws IOException {
		
		String url = "https://finance.naver.com/sise/entryJongmok.nhn?&page=";
		Document doc;
		Elements stockItems;
		
		for(int i=1; i<=1; i++) {
			StockItem stockItem = new StockItem();
			
			doc = Jsoup.connect(url+i).get();
			stockItems = doc.select("a[href*=/item/]");
					
//			for(int j=0; j<stockItems.size(); j++) {
			for(int j=0; j<2; j++) {
				Element stock = stockItems.get(j); 
				
				String stockNm = stock.text();
				String stockNo = stock.attr("href").split("=")[1];
				System.out.println((i-1)*10+j+1 + " - stockNm: " + stockNm + ", stockNo: " + stockNo);
				
				stockItem.setStockNm(stockNm);
				stockItem.setStockNo(stockNo);
				stockItem.setStockRank((i-1)*10+j+1);
				
				setListStockItemInfoDetail(stockNo);
				
				
//				stockAnalService.insertStockItemInfo(stockItemVO);
				stockAnalService.saveItemStock(stockItem);
//				this.setStockItemDetailInfo(stockNo);
			}
		}
		
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
		Date curDate =  new Date();
		String today = formatter.format(curDate);
//		System.out.println("#####!!!!!today: " + today);
		
		
		
		return "redirect:/getStockItemInfoList.do";
        
    }
	
	@RequestMapping("/getStockItemInfoList.do")
	public String getStockItemInfo(Model model) throws IOException {
		
		List<StockItem> stockItemList = stockAnalService.findItems();
        model.addAttribute("stockItemList", stockItemList);
        return "stockItemList";
	}
	
	
	
	
	@RequestMapping("/setListStockItemInfoDetail.do")
	public void setListStockItemInfoDetail(String stockNo) throws IOException {	
		StockItemDetail stockItemDetail = new StockItemDetail();
		stockItemDetail.setStockNo(stockNo);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd");
		Date curDate =  new Date();
		String today = formatter.format(curDate);
//		System.out.println("#####!!!!!today: " + today);
		
		String maxStockDate = stockAnalService.findOneMaxStockDateDetail();
		maxStockDate = maxStockDate==null? "0000.00.00":maxStockDate;
//		System.out.println("###@@@@@@@@@@@@@@@@maxStockDate: " + maxStockDate);
		
		String urlDetail = "https://finance.naver.com/item/sise_day.nhn?code="+stockNo+"&page=";
		Document docDetail;
		Elements stockItemsDetail;
		
		int k = 1;
		do {
			docDetail = Jsoup.connect(urlDetail+k).get();
//			System.out.println("###@@@@@@@@@@@@@@@@ today.compareTo(maxStockDate): " + today.compareTo(maxStockDate));
			if(docDetail != null) {
//			if(today.compareTo(maxStockDate) > 0) {
				stockItemsDetail = docDetail.select("table.type2 td:has(span)");
				
				for(int j=0; j<stockItemsDetail.size(); j++) {
					Element stockDetail = stockItemsDetail.get(j);
					String stockTxt = stockDetail.text();
					
//					System.out.println("alt: " + stockDetail.toString().contains("alt=\"하락\""));
//					System.out.println("stockDetail: " + stockDetail);
//					System.out.println("!!!stockTxt: " + stockTxt);
//					System.out.println("!!!후 j: " + j);
						if(j%7 == 0) {stockItemDetail.setStockDate(stockTxt);}
//						if(today.compareTo(maxStockDate) > 0) {
						if(j%7 == 1) {stockItemDetail.setClosingPrice(Integer.valueOf(stockTxt.replaceAll(",", "")));}
						if(j%7 == 2) {
							stockItemDetail.setNetChange(Integer.valueOf(stockTxt.replaceAll(",", "")));
						
							if(stockDetail.toString().contains("alt=\"상승\"")) {
								stockItemDetail.setNetChangeSign("up");
							}
							else if(stockDetail.toString().contains("alt=\"하락\"")) {
								stockItemDetail.setNetChangeSign("down");
							}
							else {stockItemDetail.setNetChangeSign("");}
						}
						if(j%7 == 3) {stockItemDetail.setOpeningPrice(Integer.valueOf(stockTxt.replaceAll(",", "")));}
						if(j%7 == 4) {stockItemDetail.setHighPrice(Integer.valueOf(stockTxt.replaceAll(",", "")));}
						if(j%7 == 5) {stockItemDetail.setLowPrice(Integer.valueOf(stockTxt.replaceAll(",", "")));}
						if(j%7 == 6) {
							stockItemDetail.setStockVolume(Integer.valueOf(stockTxt.replaceAll(",", "")));
							stockAnalService.saveItemStockDetail(stockItemDetail);
						}
				}
			}
			
			k++;
		} while(k<2);
//	    } while(docDetail != null);
		
	}
	
	
	
	
	@RequestMapping(value = "/{stockNo}/detail", method = RequestMethod.GET)
    public String updateItemForm(@PathVariable("stockNo") String stockNo, Model model) {

		StockItem stockItem = stockAnalService.findOne(stockNo);
        model.addAttribute("stockItem", stockItem);
        return "items/stockItemDetail";
    }
}
