package drosa.entity;

import java.math.BigDecimal;

import drosa.utils.PriceFormat;

/**
 * Prices are stored
 * @author DAVID
 *
 */
public class Price {
	long id = -1;
	String currency	= ""; 	// stored in upperCase format
	StringBuilder timestamp	= new StringBuilder(23); 	//date stored as StringBuilder->no operations with it
	int lastBid = -1;		//to avoid precission loss and not create toomany objects with BigDecimal
	int lastAsk = -1;		//to avoid precission loss and not create toomany objects with BigDecimal
	int decimalPlaces = 4; //by default EUR/USD GBP/USD cases, JPY pairs has 2
		
	public Price() {
		//default constructor
	}
	
	public Price(Price price) {
		id			= price.getId();
		currency	= price.getCurrency();
		timestamp.replace(0,this.timestamp.length(),price.getTimestamp());
		lastBid 	= price.getLastBid();
		lastAsk 	= price.getLastAsk();
		decimalPlaces = price.getDecimalPlaces();
	}

	public Price(long id, String currency,String timestampStr,int bid,int ask,int decimalPlaces) {
		this.id				= id;
		this.currency		= currency;
		this.timestamp.replace(0,this.timestamp.length(),timestampStr);
		this.lastBid 		= bid;
		this.lastAsk 		= ask;
		this.decimalPlaces	= decimalPlaces;
	}
		
	public String getTimestamp() {
		return timestamp.toString();
	}

	public void setTimestamp(String timestampStr) {
		this.timestamp.replace(0,this.timestamp.length(),timestampStr);
	}

	public int getLastBid() {
		return lastBid;
	}

	public void setLastBid(int lastBid) {
		this.lastBid = lastBid;
	}

	public int getLastAsk() {
		return lastAsk;
	}

	public void setLastAsk(int lastAsk) {
		this.lastAsk = lastAsk;
	}

	public int getDecimalPlaces() {
		return decimalPlaces;
	}

	public void setDecimalPlaces(int decimalPlaces) {
		this.decimalPlaces = decimalPlaces;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	public void copy(Price price) {
		id			= price.getId();
		currency	= price.getCurrency();
		timestamp.replace(0,this.timestamp.length(),price.getTimestamp());
		lastBid 	= price.getLastBid();
		lastAsk 	= price.getLastAsk();		
	}

	public void init(long id,String currency,String timestampStr,int bid,int ask) {
		this.id			= id;
		this.currency	= currency;
		this.timestamp.replace(0,this.timestamp.length(),timestampStr);
		this.lastBid 	= bid;
		this.lastAsk 	= ask;			
	}
	
	@Override
	public String toString() {
		return new StringBuilder(id+" ")
				.append(",").append(currency)
				.append(",").append(timestamp)
				.append(",").append(lastBid)
				.append(",").append(lastAsk)
				.toString();
	}
	
}
