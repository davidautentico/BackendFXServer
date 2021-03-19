package drosa.test;

import drosa.backend.BackendFX;
import drosa.client.ClientFX;


/**
 * For testing purposes, several messages are feeed into the backend and a simple client
 * consumes them
 * @author DAVID
 *
 */
public class PriceConsumerDemo {


	public static void main(String[] args) throws InterruptedException {
		String newLine = System.getProperty("line.separator");
		//messages
		String msg1 ="106,EUR/USD,1.1000,1.2000,01-06-2020 12:01:01:001";
		String msg2 ="107,EUR/JPY,119.60,119.90,01-06-2020 12:01:01:002";
		String msg3 ="108,GBP/USD,1.2500,1.2560,01-06-2020 12:01:01:002";
		String msg4 ="109,GBP/USD,1.2499,1.2561,01-06-2020 12:01:01:100";
		String msg5 ="110,EUR/JPY,119.61,119.91,01-06-2020 12:01:01:110";
		String msg6 = "111,EUR/USD,1.1050,1.2004,01-06-2020 12:01:01:201"
				.concat(newLine)
				.concat("112,GBP/USD,1.2496,1.2541,01-06-2020 12:01:01:220")
				;
		
		//Start backend
		BackendFX backend = new BackendFX();
		backend.init();
		
		System.out.println("***BACKEND STARTED**");
		
		//Create a client
		ClientFX clientFX = new ClientFX(backend.getAPI());
				
		//simulating arriving
		backend.processMessage(msg1);
		backend.processMessage(msg2);
		backend.processMessage(msg3);
		backend.processMessage(msg4);
		backend.processMessage(msg5);
		backend.processMessage(msg6);
		
		//simulating consuming
		int count = 0;
		while (count<=10){
			System.out.println("Try..."+count);
			clientFX.readLastPrice("EUR/USD");
			clientFX.readLastPrice("GBP/USD");
			clientFX.readLastPrice("EUR/JPY");
			Thread.sleep(100);
			count++;
		}
		
		//Stop backend
		backend.stop();
		
		System.out.println("***BACKEND STOPPED***");
	}

}

