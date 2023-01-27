package Modelo;

import java.util.ArrayList;
import java.util.function.Predicate;
import java.util.List;
import java.util.stream.Collectors;


public class LambdaFirewallDecorator extends ActorDecorator {

	List<Predicate<MessageInterface>> predicateList = new ArrayList<>();

	public LambdaFirewallDecorator(ActorInterface Actor, Predicate<MessageInterface> predicate) {
		super(Actor);
		this.predicateList.add(predicate);

	}
	@Override
	public void send(MessageInterface message){
	 if (message instanceof Message) {
		 List<Predicate<MessageInterface>> auxlength = predicateList.stream()
				 .filter(p -> p.test(message))
				 .collect(Collectors.toList());

		 //si esta en el predicado
		 if (auxlength.size() == predicateList.size()) {
			 System.out.println("lambda");
			 super.send(message);
		 }
	 }else{
			 predicateList.add(((AddClosureMessage)message).getPredicate());
		 }




	}


}



