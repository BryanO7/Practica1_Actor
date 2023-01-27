package Modelo;

import org.junit.Test;
public class Tests {
	public ActorContext ActorED= ActorContext.getInstance();



	@Test
	public void testRingActor(){
		ActorProxy ringArray[] = new ActorProxy[10];
		ringArray[0]= ActorContext.spawnActor(new RingActor("ring0"));
		for(int i=1; i<10; i++){
			String name= "ring"+i;
			ringArray[i]= ActorContext.spawnActor(new RingActor(name));
			//ringArray[i-1].getRingActor().nextActor(ringArray[i].getRingActor());
			((RingActor)ringArray[i-1].getActor()).nextActor((RingActor)ringArray[i].getActor());
		}
		//ringArray[9].getRingActor().nextActor(ringArray[0].getRingActor());
		((RingActor)ringArray[9].getActor()).nextActor((RingActor)ringArray[0].getActor());


		ringArray[0].send(new Message(ringArray[0], "a p"));
	}

	@Test
	public void testPingPong(){
		ActorProxy primero = ActorContext.spawnActor( new PingPong("first"));
		ActorProxy segundo = ActorContext.spawnActor(new PingPong ("second"));
		//primero.getPingPong().setPartner(segundo.getPingPong());
		((PingPong)primero.getActor()).setPartner((PingPong)segundo.getActor());

		//segundo.getPingPong().setPartner(primero.getPingPong());
		((PingPong)segundo.getActor()).setPartner((PingPong)primero.getActor());
		primero.send(new Message(segundo,"porque"));


	}


	@Test
	public void InsulterService() {
		ActorProxy actor1 = ActorContext.spawnActor( new InsultActor("hola"));
		InsultServiceInterface insultador = (InsultServiceInterface) DynamicProxy.getProxy(new InsultService(), actor1);
		insultador.addInsult(new Message(null, " tonto"));
		//insultador.addInsult(new Message(null, " tontito"));
		//insultador.addInsult(new Message(null, " idiot"));
		//insultador.addInsult(new Message(null, " troll"));
		//insultador.addInsult(new Message(null, " malo"));
		//insultador.addInsult(new Message(null, " peste"));
		//insultador.addInsult(new Message(null, " malardo"));
		//insultador.addInsult(new Message(null, " antiguo"));
		
		System.out.print(insultador.getInsult());

	}
	@Test
	public void Encrypt (){

		ActorProxy encrypt = ActorContext.spawnActor( new EncryptionDecorator(new Actor("enc")));
		ActorProxy actor1 = ActorContext.spawnActor( new FirewallDecorator(new Actor("fire1")));
		ActorProxy actor2 = ActorContext.spawnActor(new FirewallDecorator(new EncryptionDecorator(new Actor("fire2"))));
		ActorProxyMain main = ActorContext.spawnActormain(new EncryptionDecorator(new Actor("enc")));
		ActorProxy lambda = ActorContext.spawnActor( (new LambdaFirewallDecorator(new Actor("lambdonorris"), x -> x.getMessage()!=null)));

		encrypt.send(new Message(actor1,"encriptacion goes brr"));
		actor2.send(new Message(actor2,"encriptao"));
		actor1.send(new Message(main, "mensajito al main"));
		lambda.send(new AddClosureMessage(x->x.getMessage().equals("lando")));
		actor2.send(new Message(null,"El nano"));
		lambda.send(new Message(null,"lando"));

	}
	@Test
	public void insultos() throws InterruptedException {
		ActorProxyMain insultador = ActorContext.spawnActormain( new InsultActor("insultos"));
		insultador.send(new AddInsultMessage(insultador,"bobo"));
		insultador.send(new AddInsultMessage(insultador,"idiot"));
		insultador.send(new AddInsultMessage(insultador,"retard"));
		insultador.send(new AddInsultMessage(insultador, "kys"));
		insultador.send(new GetInsultMessage(insultador, ""));
		insultador.send(new GetAllInsultsMessage(insultador, ""));


		MessageInterface insulto = insultador.recieve();
		System.out.println(insulto.getMessage());
		insulto = insultador.recieve();
		System.out.println(insulto.getMessage());

		}


	}


