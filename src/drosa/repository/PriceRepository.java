package drosa.repository;

import java.util.ArrayList;
import java.util.HashMap;

import drosa.entity.Price;

/**
 * Margin corrections here
 * @author DAVID
 *
 */
public class PriceRepository {

	//HashMap with lastPrice for each currency
	final HashMap<String,Price> priceRepository = new HashMap<String,Price>();
	
	
	public PriceRepository(){}
	
	
	final String NEWLINE = System.getProperty("line.separator");
	/**
	 * Key is upperCase just to avoid these kind of problems eurusd!=EURUSD
	 * 
	 * @param currencyList
	 */
	public PriceRepository(ArrayList<String> currencyList){
		for (String curr : currencyList){
			priceRepository.put(curr.toUpperCase(),new Price());			
		}
	}
	
	/**
	 * Several lines at a time are possible
	 * Date not formatted, it's assumed that price will be delivered in JSON format
	 * @param price
	 */
	public void putPrice(String price){
		String[] lines = price.split(NEWLINE);
		
		for (int i=0;i<lines.length;i++){
			String[] values = lines[i].split(",");
			try{
				Price p = new Price();
				p.setId(Long.valueOf(values[0]));
				p.setCurrency(values[1]);			
				//Internally it works with 'int' type to avoid loss precission
				//BigDecimal is not used to avoid creating too many objects
				p.setLastBid((int) Math.round(Integer.valueOf(values[2].replace(".", ""))*0.999));
				p.setLastAsk((int) Math.round(Integer.valueOf(values[3].replace(".", ""))*1.001));
				p.setTimestamp(values[4]);
				p.setDecimalPlaces(values[2].length()-values[2].indexOf('.')-1);
				//price is stored into hashmap
				priceRepository.put(values[1],p);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	public void putPrice(Price price){
		Price repoPrice = priceRepository.get(price.getCurrency());
		if (repoPrice==null)
			priceRepository.put(price.getCurrency(),new Price(price));
		else repoPrice.copy(price);		
	}
	
	public double getLastBid(String curr){
		return priceRepository.get(curr.toUpperCase()).getLastBid();
	}
	public double getLastAsk(String curr){
		return priceRepository.get(curr.toUpperCase()).getLastAsk();
	}
	public Price getPrice(String curr){
		return priceRepository.get(curr.toUpperCase());
	}
}
