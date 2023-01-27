package Modelo;

import java.util.concurrent.LinkedBlockingQueue;

public class ActorDecorator implements ActorInterface {
	ActorInterface actor;


	public ActorDecorator(ActorInterface actor) {
		this.actor = actor;
	}

	@Override
	public void send(MessageInterface message) {
		actor.send(message);
	}

	@Override
	public void process(MessageInterface message) throws InterruptedException {
		actor.process(message);
	}

	@Override
	public LinkedBlockingQueue<MessageInterface> getQueue() {

		return actor.getQueue();
	}

	@Override
	public String getName() {
		return actor.getName();
	}

	@Override
	public void run() {
	while (true) {
		try{
			process(actor.getQueue().take());
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}
	}

	@Override
	public String toString(){
		return actor.toString();
	}
}