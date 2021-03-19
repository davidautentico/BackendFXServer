package drosa.backend;

import drosa.entity.Price;
import drosa.repository.PriceRepository;

/**
 * API REST.. to get the prices
 * Not JSON format..
 * Just simulating..
 * @author DAVID
 *
 */
public class PriceAPI {

	
	PriceRepository priceRepo;
	
	public PriceAPI(PriceRepository priceRepo){
		this.priceRepo = priceRepo;
	}
	
	/*
	 *@GET
	 */
	public String getLastBid(String currency){				
		Price price = priceRepo.getPrice(currency);
		if (price!=null)
			return String.valueOf(price.getLastBid());
		return "";
	}
	
	/*
	 *@GET
	 */
	public String getLastAsk(String currency){
		Price price = priceRepo.getPrice(currency);
		if (price!=null)
			return String.valueOf(price.getLastAsk());
		return "";
	}
	
	/*
	 *@GET
	 */
	public String getLastPrice(String currency){
		
		Price price = priceRepo.getPrice(currency);
		if (price!=null)
			return price.toString();
		return "";
	}
}
