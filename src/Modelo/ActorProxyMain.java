package Modelo;

import java.util.concurrent.LinkedBlockingQueue;

public class ActorProxyMain implements ActorInterface,Runnable{
	private ActorInterface actor;
	private LinkedBlockingQueue<MessageInterface> queue;


	public ActorProxyMain(ActorInterface actor){
			this.actor=actor;
			queue = new LinkedBlockingQueue<MessageInterface>();

	}
	@Override
	public void send(MessageInterface message) {
		message.setSender(this);
		System.out.println("el sender es "+this);
		if (message instanceof Message){
			System.out.println("hola");
			queue.add(message);
		} else actor.send(message);
	}

	@Override
	public void process(MessageInterface message) throws InterruptedException {

	}

	@Override
	public LinkedBlockingQueue<MessageInterface> getQueue() {
		return queue;
	}

	@Override
	public String getName() {
		return actor.getName();
	}

	@Override
	public void run() {

	}
	public MessageInterface recieve() throws InterruptedException {

		return queue.take();
	}

}
