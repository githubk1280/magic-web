package com.magic.crawler;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.Selectable;

import com.magic.processor.MysqlLocalDao;

public class Magic163Processor implements PageProcessor {

	private Site site = Site
			.me()
			.setRetryTimes(1)
			.setCharset("utf-8")
			.setUserAgent(
					"Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/37.0.2062.94 Safari/537.36");
	
	public void process(Page page) {
		for(int i=2;i<29;i++){
			for(int j=1;j<14;){
//				System.out.println(i+"---"+j);
				String winNumber = getValue(page.getHtml(), 
						"/html/body/article/section/div[1]/table/tbody/tr["+i+"]/td["+j+"]/@data-win-number");
				String term = getValue(page.getHtml(), 
						"/html/body/article/section/div[1]/table/tbody/tr["+i+"]/td["+j+"]/text()");
				if(StringUtils.isNotEmpty(winNumber)){
					int [] winNums = parseWinNum(winNumber);
					Date d = new Date();
					try {
						d = parseTerm(page.getUrl().toString());
					} catch (ParseException e) {
						e.printStackTrace();
					}
					int termId = Integer.parseInt(term);
					System.out.println("win number="+winNums[0]+"-"+winNums[1]+"-"+winNums[2]+" @["+termId+"]"+" date="+d.toString());
					try {
						MysqlLocalDao.insert163(winNums, d, termId);
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				j+=6;
			}
		}
	}

	private Date parseTerm(String url) throws ParseException {
		return new SimpleDateFormat("yyyyMMdd").parse(url.substring(url.indexOf("date=")+5));
	}

	private int[] parseWinNum(String winNumber) {
		int result [] = new int [3];
		String [] nums = winNumber.split(" ");
		for(int i=0;i<3;i++){
			result[i] = Integer.parseInt(nums[i]);
		}
		return result;
	}

	public Site getSite() {
		return site;
	}
	
	private String getValue(Html html, String xpath) {
		try {
			Selectable selector = html.xpath(xpath);
			if (selector != null) {
				return selector.toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
