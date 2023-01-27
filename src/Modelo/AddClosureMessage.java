package Modelo;

import java.util.function.Predicate;

public class AddClosureMessage implements MessageInterface{
	Predicate<MessageInterface> predicate;
	ActorInterface sender;

	public AddClosureMessage( Predicate<MessageInterface> predicate){

		this.predicate=predicate;
	}


	public Predicate<MessageInterface> getPredicate(){

		return this.predicate;
	}

	@Override
	public String getMessage() {

		return null;
	}

	@Override
	public void setMessage(String message) {

	}

	@Override
	public ActorInterface getSender() {

		return sender;
	}

	@Override
	public void setSender(ActorInterface sender) {

		this.sender=sender;
	}
}
