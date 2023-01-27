package Modelo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DynamicProxy implements InvocationHandler {
	private Object sender;
	private ActorInterface actor;

	public DynamicProxy(Object sender, ActorInterface actor){
		this.sender=sender;
		this.actor=actor;
	}


	public static Object getProxy(Object sender, ActorInterface actor){
		return java.lang.reflect.Proxy.newProxyInstance(sender.getClass().getClassLoader(),
				sender.getClass().getInterfaces(),
				new DynamicProxy(sender,actor)
		);
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
		//actor.send(new Message(new InsultActor("el que te insulta"), "bobo o que"));

		Object result = method.invoke(sender,args);
		return result;
	}
}
