package Modelo;

import java.util.concurrent.LinkedBlockingQueue;

public class PingPong implements ActorInterface{
	private String name;
	private int veces;
	private LinkedBlockingQueue<MessageInterface> queue;
	private  PingPong partner;



	public PingPong(String name){
		this.name=name;
		queue= new LinkedBlockingQueue<>();
		this.veces=0;

	}

	public void setPartner(PingPong p){
		partner=p;
	}
	@Override
	public void send(MessageInterface message) {
		if (message instanceof Message){
			this.queue.add(message);

			message.setSender(this);





			veces++;
			if(this.partner.veces <5)
				this.partner.send(message);
			else this.partner.send(new QuitMessage());

		}else this.queue.add(message);
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
	public String getName() {
		return name;
	}

	@Override
	public void run() {
		while (true){
			try {

				MessageInterface aux =this.queue.take();

				System.out.println(aux.getClass());
				if (aux instanceof Message){
					System.out.println("runneado");
					process(aux);
				}else break;
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}

		}
	}
	public int getVeces(){
		return veces;
	}
	@Override
	public String toString() {return "Modelo.PingPong{" +
				


				'}';
	}
}
