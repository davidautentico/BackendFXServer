package drosa.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import drosa.repository.PriceRepository;

/**
 * Get messages from the queue and put them in repository
 * @author DAVID
 *
 */
public class PriceQueueHandler implements Runnable {

	BlockingQueue<String> blockingQueue;
	PriceRepository priceRepo;
	
	private Thread worker;
    private final AtomicBoolean running = new AtomicBoolean(false);
	
	public PriceQueueHandler(BlockingQueue<String> blockingQueue,PriceRepository priceRepo){
		this.blockingQueue = blockingQueue;
		this.priceRepo = priceRepo;
	}
	
	public void start() {
        worker = new Thread(this);
        worker.start();
    }
	
	public void stop() {
        running.set(false);
    }

	@Override
	public void run() {
		running.set(true);
		while(running.get()){
			try {
				if (!blockingQueue.isEmpty()){
					String msg = blockingQueue.take(); //take from de queue
					priceRepo.putPrice(msg);//to price repository
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
		
	}
}
