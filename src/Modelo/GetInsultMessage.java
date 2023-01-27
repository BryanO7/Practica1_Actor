package Modelo;

public class GetInsultMessage implements MessageInterface{
	String message;
	ActorInterface sender;


	public GetInsultMessage(ActorInterface sender, String message){
		this.message	= message;
		this.sender		= sender;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public void setMessage(String message) {
		this.message= message;
	}

	@Override
	public ActorInterface getSender() {
		return sender;
	}

	@Override
	public void setSender(ActorInterface sender) {
		this.sender= sender;
	}

	public String toString() {
		return "El mensaje fue enviado por" + this.sender + "\nMSG getinsultmessage: "+this.message;
	}
}
