package drosa.utils;

public class PriceFormat {
	
	public static String getString(int price,int decimalPlaces){

		String priceStr = String.valueOf(price);
		
		//Price could be below 1.0 so the int value could be 9934
		//translating that to float value -> 0.9943
		//this is very possible for GBPUSD values historically speaking
		if (priceStr.length()==decimalPlaces)
			return "0.".concat(priceStr);
		
		return priceStr.substring(0, priceStr.length()-decimalPlaces)
				.concat(".")
				.concat(priceStr.substring(priceStr.length()-decimalPlaces,priceStr.length()));
	}

}
