package com.smigic.sensorsReader.stream;

public class StreamClient<T> extends Observer {
	
	private Subject<T> subject;
	
	public StreamClient(Subject<T> subject) {
		subject.attach(this);
		this.subject = subject;
	}

	public void publishMsg(T msg){
		this.subject.setState(msg);
	}

	public T getMsg(){
		return this.subject.getState();
	}

	@Override
	protected void update() {
		this.subject.getState();
	}

}
