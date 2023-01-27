package Modelo;

public interface MessageInterface {
	String getMessage();
	void  setMessage( String message);
	ActorInterface getSender();
	void setSender(ActorInterface sender);
}

