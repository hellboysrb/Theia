package com.smigic.sensorsReader.stream;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject<T> {
	
	private List<Observer> observers = new ArrayList<>();
	
	public void attach(Observer observer) {
		observers.add(observer);
	}
	
	public void detach(Observer observer){
		observers.remove(observer);
	}
	
	protected void notifyObservers() {
		for (Observer observer : observers) {
			observer.update();
		}
	}

	protected abstract void setState(T msg);

	protected abstract T getState();
}
