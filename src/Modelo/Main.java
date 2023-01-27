package Modelo;

public class Main {
	public static void main(String[] args) throws InterruptedException {


		ActorContext ed = ActorContext.getInstance();
		//Modelo.ActorProxy hello = Modelo.ActorContext.spawnActor("name",new Modelo.RingActor());
		//hello.send(new Modelo.Message(null,"Hello World"));

		/*Modelo.ActorProxyMain insult1= Modelo.ActorContext.spawnActormain("insult1",new Modelo.InsultActor());
		Modelo.ActorProxyMain insult= Modelo.ActorContext.spawnActormain("insult",new Modelo.InsultActor());
		insult.send(new Modelo.AddInsultMessage(insult,"adios"));
		//insult1.toString();
		insult.send(new Modelo.GetInsultMessage(insult,""));

		Modelo.MessageInterface result = insult.recieve();
		System.out.println(result.getMessage());
*/
		//Modelo.ActorProxy hola = Modelo.ActorContext.spawnActor("name", new Modelo.EncryptionDecorator(new Modelo.RingActor("a")));
		//Modelo.ActorProxy hola2 = Modelo.ActorContext.spawnActor("name1", (new Modelo.RingActor("b")));
		//hola2.send(new Modelo.Message(hola,"CR7 goat"));
		//Modelo.ActorProxy hola3 = Modelo.ActorContext.spawnActor("name", new Modelo.FirewallDecorator(new Modelo.RingActor("a")));
		//Modelo.ActorProxy noesno = Modelo.ActorContext.spawnActor("hello", new Modelo.FirewallDecorator(new Modelo.RingActor("c")));
		//noesno.send(new Modelo.Message(hola3,"bobo o que "));

		//Modelo.ActorProxy encrypt = Modelo.ActorContext.spawnActor("name", new Modelo.EncryptionDecorator(new Modelo.RingActor("a")));
		//Modelo.ActorProxy sender = Modelo.ActorContext.spawnActor("name1", (new Modelo.FirewallDecorator(new Modelo.Actor("b"))));

		//Modelo.ActorProxy lambaDecorator = Modelo.ActorContext.spawnActor("name", (new Modelo.LambdaFirewallDecorator(new Modelo.Actor("f"), x -> x.getMessage()!=null)));


		//lambaDecorator.send(new Modelo.AddClosureMessage(x->x.getMessage().equals("amigo")));
		//sender.send(new Modelo.Message(lambaDecorator, "hola"));
		//lambaDecorator.send(new Modelo.Message(sender,"amigo"));
		//lambaDecorator.send(new Modelo.Message(sender,"asdas"));

		/*Modelo.ActorProxy actor1 = Modelo.ActorContext.spawnActor("hola", new Modelo.InsultActor("hola" ));
		Modelo.InsultServiceInterface insultador = (Modelo.InsultServiceInterface) Modelo.DynamicProxy.getProxy(new Modelo.InsultService(), actor1);
		insultador.addInsult(new Modelo.Message(null, " tonto"));

		insultador.addInsult(new Modelo.Message(null, " idiot"));
		insultador.addInsult(new Modelo.Message(null, " troll"));
		insultador.addInsult(new Modelo.Message(null, " ugly"));



		System.out.print(insultador.getInsult());
*/

	/*	Modelo.ActorProxy encrypt = Modelo.ActorContext.spawnActor( new Modelo.EncryptionDecorator(new Modelo.Actor("enc")));
		Modelo.ActorProxy actor1 = Modelo.ActorContext.spawnActor( new Modelo.FirewallDecorator(new Modelo.Actor("fire1")));
		Modelo.ActorProxy actor2 = Modelo.ActorContext.spawnActor(new Modelo.FirewallDecorator(new Modelo.EncryptionDecorator(new Modelo.Actor("don"))));
		Modelo.ActorProxyMain main = Modelo.ActorContext.spawnActormain( new Modelo.EncryptionDecorator(new Modelo.Actor("enc")));
		Modelo.ActorProxy lambda = Modelo.ActorContext.spawnActor((new Modelo.LambdaFirewallDecorator(new Modelo.Actor("lambdonorris"), x -> x.getMessage()!=null)));

		encrypt.send(new Modelo.Message(actor1,"encriptacion goes brr"));
		actor2.send(new Modelo.Message(actor2,"encriptao"));
		actor1.send(new Modelo.Message(main, "mensajito al main"));
		lambda.send(new Modelo.AddClosureMessage(x->x.getMessage().equals("lando")));
		actor1.send(new Modelo.Message(lambda,"El nano"));
		lambda.send(new Modelo.Message(actor1,"null"));

	/*	Modelo.ActorProxyMain insultador = Modelo.ActorContext.spawnActormain("insultador", new Modelo.InsultActor("insultador"));

		insultador.send(new Modelo.AddInsultMessage(insultador,"bobo"));
		insultador.send(new Modelo.AddInsultMessage(insultador,"idiot"));
		insultador.send(new Modelo.AddInsultMessage(insultador,"retard"));
		insultador.send(new Modelo.AddInsultMessage(insultador, "kys"));
		insultador.send(new Modelo.GetInsultMessage(null, null ));


		Modelo.MessageInterface insulto = insultador.recieve();
		System.out.println(insulto);
*/
		ActorProxy primero = ActorContext.spawnActor( new PingPong("first"));
		ActorProxy segundo = ActorContext.spawnActor(new PingPong ("second"));
		//primero.getPingPong().setPartner(segundo.getPingPong());
		((PingPong)primero.getActor()).setPartner(((PingPong)segundo.getActor()));

		//segundo.getPingPong().setPartner(primero.getPingPong());
		((PingPong)segundo.getActor()).setPartner(((PingPong)primero.getActor()));
		primero.send(new Message(segundo,"porque"));

	}
}