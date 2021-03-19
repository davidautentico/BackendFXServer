package drosa.client;
import drosa.backend.PriceAPI;

/**
 * Not using HTTP client, just simulating API rest
 * @author DAVID
 *
 */
public class ClientFX {
	
	
	PriceAPI priceAPI;
	public ClientFX(PriceAPI priceApi){
		this.priceAPI = priceApi;
	}

	public void readLastPrice(String currency){
		System.out.println("Last price for "+currency+": "+priceAPI.getLastPrice(currency));
	}
}
