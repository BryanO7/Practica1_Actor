package Modelo;


public class EncryptionDecorator extends ActorDecorator {


	public EncryptionDecorator(ActorInterface actor){
		super(actor);
	}




	@Override
	public void send(MessageInterface message){

		message.setMessage(this.encrypt(message,1));
		System.out.println("Encrypted message :" + message.getMessage());
		super.send(message);

	}


	@Override
	public void process(MessageInterface message) throws InterruptedException {

		message.setMessage(this.decrypt(message.getMessage(), 1));
		System.out.println("Decrypt message :" +message.getMessage());

		super.process(message);
	}

	public String encrypt(MessageInterface message, int secret){
		char ch;
		String encrypt= "";

			for (char c: message.getMessage().toCharArray())
			{
				if (c== ' '){
					encrypt+= ' ';
					continue;
				}
				int asci = c;
				asci+= secret;
				ch = (char) asci;
				encrypt += ch;

			}
			return encrypt;

	}

	public String decrypt (String encrypt, int secret){
		String decrypt= "";
		char ch;
		for (char c: encrypt.toCharArray())
		{
			if (c== ' '){
				decrypt+= ' ';
				continue;
			}
			int asci = c;
			asci-= secret;
			ch = (char) asci;
			decrypt += ch;

		}
		return decrypt;

	}
	public String toString() {
		return  actor.toString();
	}

}
