package drosa.backend;

import java.util.concurrent.BlockingQueue;

import drosa.messenger.MessageCustomInterface;

public class PriceConsumer implements MessageCustomInterface {

	BlockingQueue<String> blockingQueue;
	
	public PriceConsumer(BlockingQueue<String> blockingQueue){
		this.blockingQueue = blockingQueue;
	}
	
	@Override
	public void onMessage(String message) {
		try {
			blockingQueue.put(message);
		} catch (InterruptedException e) {
			//print error
			e.printStackTrace();
		}
	}
}
