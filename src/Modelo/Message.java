package Modelo;

public class Message implements MessageInterface{
	String message;
	ActorInterface sender;




	public Message(ActorInterface sender, String message){
		this.message=message;
		this.sender=sender;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message = message;

	}

	@Override
	public ActorInterface getSender() {

		return sender ;
	}

	@Override
	public void setSender(ActorInterface sender) {
		this.sender= sender;

	}

	@Override
	public String toString() {
		return  "MSG: "+this.message;
	}

	public String getText(){
		return "\n El mensaje es: "+this.message+ "\n lo envio " +this.sender ;
	}

}
