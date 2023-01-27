package Modelo;

import java.util.concurrent.LinkedBlockingQueue;

public class ActorProxy implements ActorInterface, Runnable {

	private ActorInterface actor;



	public ActorProxy (ActorInterface act){
		actor= act;

	}

	public ActorInterface getActor() {
		return  this.actor;
	}

	public void setActor(ActorInterface actor) {
		this.actor = actor;
	}

	@Override
	public void send(MessageInterface message) {
		actor.send(message);

	}

	@Override
	public void process(MessageInterface message) {

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
		// TODO Auto-generated method stub

	}




	//public PingPong getPingPong() {return (PingPong) this.actor;}
	//public RingActor getRingActor(){return (RingActor) this.actor;}
	public String toString() {
		return  actor.toString();
	}
}
