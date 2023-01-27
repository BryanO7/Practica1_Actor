package Modelo;

import java.util.concurrent.LinkedBlockingQueue;

public class RingActor implements Runnable, ActorInterface {

	LinkedBlockingQueue<MessageInterface> queue;

	private String actorname;
	private int times;
	private RingActor nextActor;

	public RingActor(String actorname) {
		queue= new LinkedBlockingQueue<MessageInterface>();
		this.actorname = actorname;
		this.times= 0;
		nextActor= null;

	}
	@Override
	public void run() {
		while(true) {
			try {
				MessageInterface currentmessage= this.queue.take();
				if(currentmessage instanceof Message)
					process(currentmessage);

				else break;

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
	}

	public void nextActor(RingActor actor){
		nextActor=actor;
	}
	public void process(MessageInterface message) {

		System.out.println(message);

	}


	@Override
	public LinkedBlockingQueue<MessageInterface> getQueue() {
		// TODO Auto-generated method stub
		return queue;
	}

	@Override
	public String getName() {
		return actorname;
	}

	@Override
	public void send(MessageInterface message) {
		if (message instanceof Message){
			this.queue.add(message);

			times++;



			if(this.nextActor.times<100){
				message.setSender(this);

				this.nextActor.send(message);


			}else this.nextActor.send(new QuitMessage());

		}


	}

	public void setNextActor (RingActor actor){
		nextActor = actor;
	}

 	public String toString(){
		return "Modelo.RingActor " +actorname+ " Times" +times;
 	}

}
