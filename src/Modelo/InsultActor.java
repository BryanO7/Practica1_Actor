package Modelo;

import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Random;
public class InsultActor implements ActorInterface, Runnable {

	private LinkedBlockingQueue<MessageInterface> insultqueue;
	private ArrayList<MessageInterface> insultlist;
	private String name;

	public InsultActor(String name){
		insultqueue= new LinkedBlockingQueue<>();
		insultlist= new ArrayList<>();
		this.name=name;
	}
	@Override
	public void send(MessageInterface message) {

		insultqueue.add(message);
	}

	@Override
	public void process(MessageInterface message) throws InterruptedException {

		if (message instanceof Message){
		System.out.println(message);
		}
		else if(message instanceof GetInsultMessage ) {
		int rand = new Random().nextInt(insultlist.size());
		message.getSender().send((new Message(this, insultlist.get(rand).getMessage())));
				//send(new Modelo.Message(this, insultlist.get(rand).getMessage()));
		}
		else if (message instanceof AddInsultMessage) {
		insultlist.add(message);
		}
		else if (message instanceof  GetAllInsultsMessage){
				ActorInterface rec = message.getSender();
				for (MessageInterface mes : insultlist) {
					rec.send(new Message(null, mes.getMessage()));
				}

		}
	}



	@Override
	public LinkedBlockingQueue<MessageInterface> getQueue() {
		return this.insultqueue;
	}

	@Override
	public String getName() {
		return name ;
	}

	@Override
	public void run() {
		while (true) {
			try {
				MessageInterface aux = this.insultqueue.take();
				System.out.println("he pillado uno");
				process(aux);


			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				throw new RuntimeException(e);
			}
		}
	}

	public ArrayList<MessageInterface> getInsultlist() {
		return insultlist;
	}


	@Override
	public String toString(){
		return " name =  " +name;
	}
}
