package Modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InsultService implements InsultServiceInterface {
	private List<MessageInterface> insultList;
	ActorContext actorContext;



	public InsultService(){
		insultList = new ArrayList<>();
	}


	@Override
	public void addInsult(MessageInterface insult) {
		insultList.add(insult);
	}

	@Override
	public MessageInterface getInsult() {

		return insultList.get(new Random().nextInt(insultList.size()));
	}

	@Override
	public List getAllInsults() {
		return insultList;
	}
}
