package Modelo;

public class FirewallDecorator extends ActorDecorator {
	public FirewallDecorator(ActorInterface actor) {
		super(actor);
	}
	@Override
	public void send (MessageInterface message){
		System.out.println("firewalldecorator");
		if (message.getSender() instanceof ActorProxyMain ){
			System.out.println("Eres un ActorproxyMain");
		}



		else {
			super.send(message);
		}
	}


}
