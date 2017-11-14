package app.main.services;

import java.util.HashMap;

import org.apache.http.*;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class UpdateService {

	private static HashMap<String, Double> marketTable;

	public UpdateService() {
		marketTable = new HashMap<String, Double>();
		initMarketTable();
	}

	public void updateTable() {

	}

	public void updateStock(String ticker, double value) {
		marketTable.replace(ticker, value);
	}

	private void initMarketTable() {

	}

	public void getData(String symbol){
		DefaultHttpClient httpclient = new DefaultHttpClient();
		try {
			HttpHost target = new HttpHost("www.alphavantage.co", 80, "https");
			HttpGet getRequest = new HttpGet("/query?function=TIME_SERIES_INTRADAY&symbol="+ symbol + "&interval=15min&apikey=T43MK3VWRNJ7TNH8");

			HttpResponse httpResponse = httpclient.execute(target, getRequest);
			HttpEntity entity = httpResponse.getEntity();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
	}
}