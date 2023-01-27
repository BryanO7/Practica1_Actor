package Modelo;

import java.util.HashMap;
	import java.util.Set;

	public class ActorContext extends Thread {

		private static ActorContext actorContext;
		private static HashMap<String, ActorInterface> hash;

		private  ActorContext() {}

		public static ActorContext getInstance() {
			if (actorContext == null){
				actorContext = new ActorContext();
				hash = new HashMap<String, ActorInterface>();
			}
			return actorContext;

		}


		 public static ActorProxy spawnActor( ActorInterface actor) {
			ActorProxy aux = new ActorProxy (actor);
			new Thread(actor).start();
			hash.put(actor.getName(), actor);
			return aux;
		}

		public ActorProxy lookup(String name){
			return (new ActorProxy(hash.get(name)));

		}
		public static ActorProxyMain spawnActormain( ActorInterface actor){
			ActorProxyMain aux= new ActorProxyMain(actor);
			new Thread(actor).start();
			hash.put(actor.getName(),actor);
			return aux;
		}


		public String getNames(){

			Set<String> keys = hash.keySet();
			return (keys.toString());
		}

	}


