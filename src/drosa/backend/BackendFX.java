package drosa.backend;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import drosa.queue.PriceQueueHandler;
import drosa.repository.PriceRepository;

public class BackendFX {
	
	//big enough
	private static final int MAX_QUEUE_SIZE = 100000;
	//queue for messages
	BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>(MAX_QUEUE_SIZE);
	//price repository
	PriceRepository priceRepo = new PriceRepository(); 
	//Price API
	PriceAPI priceAPI = new PriceAPI(priceRepo);
	//queue handler
	PriceQueueHandler pqh = new PriceQueueHandler(blockingQueue,priceRepo);
	//PriceConsumer		
	PriceConsumer pc = new PriceConsumer(blockingQueue);
	
	/**
	 * Start the queue handler
	 */
	public void init(){
		 pqh.start();
	}
	
	public void processMessage(String msg){
		pc.onMessage(msg);
	}

	public PriceAPI getAPI(){
		return priceAPI;
	}

	public void stop() {
		pqh.stop();		
	}
}
