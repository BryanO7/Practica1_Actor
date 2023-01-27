package Modelo;

import java.util.concurrent.LinkedBlockingQueue;

public class Actor implements ActorInterface {

	private LinkedBlockingQueue<MessageInterface> queue;
	private String name;


	public Actor(String name) {
		this.name = name;
		queue = new LinkedBlockingQueue<>();

	}

	@Override
	public String toString() {
		return " Modelo.Actor{" +
				", name='" + name + '\'' +
				'}';
	}

	public String getName() {
		return name;
	}


	@Override
	public void send(MessageInterface message) {
		this.queue.add(message);
	}

	@Override
	public void process(MessageInterface message) throws InterruptedException {
		System.out.println(message);
	}

	@Override
	public LinkedBlockingQueue<MessageInterface> getQueue() {
		return queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				MessageInterface currentmessage = this.queue.take();
				if (currentmessage instanceof Message)
					process(currentmessage);

				else break;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
	}
}
