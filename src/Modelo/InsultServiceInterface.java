package Modelo;

import java.util.List;


public interface InsultServiceInterface {

	public void addInsult(MessageInterface insult);

	public MessageInterface getInsult();

	public List getAllInsults();

}
