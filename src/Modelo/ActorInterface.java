package Modelo;

import java.util.concurrent.LinkedBlockingQueue;

public interface ActorInterface extends Runnable {

	void send(MessageInterface message);

	public void process(MessageInterface message) throws InterruptedException;

	LinkedBlockingQueue<MessageInterface> getQueue();
	String getName();
}
