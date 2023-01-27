package Controlador;
import Modelo.*;
import javax.swing.*;


public class Controller {
	public Controller() {
	}

	ActorContext actorcontext= ActorContext.getInstance();
	ActorProxy sender = ActorContext.spawnActor(new Actor("sender"));

	public String[] listNames(DefaultListModel<String> actors) {
		String[] names = new String[actors.getSize()];
		for (int i = 0; i < actors.size(); i++) {
			names[i] = actors.get(i);
		}
		return names;
	}

	public String decoratorType(String act, String name) {
		ActorProxy Actor;
		switch (act) {
			case "Decorator":

				Actor = ActorContext.spawnActor((new FirewallDecorator(new Actor(name))));
				return name + " FireWallDecorator";


			case "Lambda":

				Actor = ActorContext.spawnActor((new LambdaFirewallDecorator(new Actor(name), x -> x.getMessage() != "hola")));
				return name + " LambdaFireWallDecorator";


			case "Encrypt":

				Actor = ActorContext.spawnActor(((new EncryptionDecorator(new Actor(name)))));
				return name + " EncryptionDecorator";

			default:
				System.out.println("error");
				break;
		}
		return null;



	}



	public void crearActor (DefaultListModel<String> actors,String text, String action, int numRing){
		ActorProxy actor;
		switch(action){

			case "InsultActor":
				ActorProxyMain insult= ActorContext.spawnActormain(new InsultActor(text));
				actors.addElement(text);

			case "RingActor":
				ActorProxy ring[]= new ActorProxy[numRing];
				ring[0] = ActorContext.spawnActor(new RingActor(text));
				for (int i=1; i<numRing; i++){
					String name = text +i;
					ring[i] = ActorContext.spawnActor(new RingActor(name));
					//ring[i-1].getRingActor().setNextActor(ring[i].getRingActor());
					((RingActor)ring[i-1].getActor()).nextActor((RingActor)ring[i].getActor());
				}
				//ring[numRing-1].getRingActor().setNextActor(ring[0].getRingActor());
				((RingActor)ring[numRing-1].getActor()).nextActor((RingActor)ring[0].getActor());
				break;


			case "PingPong":
				ActorProxy pinpong = ActorContext.spawnActor(new PingPong(text));
				ActorProxy pinpong2 = ActorContext.spawnActor(new PingPong(text+"2"));
				((PingPong)pinpong.getActor()).setPartner(((PingPong)pinpong2.getActor()));
				((PingPong)pinpong2.getActor()).setPartner(((PingPong)pinpong.getActor()));
				actors.addElement(text);
				actors.addElement(text+"2");

			default:
				System.out.println("error");
				break;


		}
	}

	public void sendMessage(String actor, String action, String msg){
		ActorInterface act;
		switch (action) {
			case "MessageT":
				act = actorcontext.lookup(actor);
				act.send(new Message(sender, msg));
				break;

			case "AddInsultMessage":
				act = actorcontext.lookup(actor);
				act.send(new AddInsultMessage(sender, msg));
				break;
			case "QuitMessage":
				act = actorcontext.lookup(actor);
				act.send(new QuitMessage());
				break;
			case "GetAllInsult":
				act =actorcontext.lookup(actor);
				act.send(new GetAllInsultsMessage(sender,msg));
				break;
		}
	}
}
