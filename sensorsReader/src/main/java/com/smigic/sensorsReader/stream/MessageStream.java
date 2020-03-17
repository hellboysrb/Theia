package com.smigic.sensorsReader.stream;

import java.util.ArrayDeque;
import java.util.Deque;

public class MessageStream<T> extends Subject<T> {

	private Deque<T> queue = new ArrayDeque<>();

	@Override
	protected void setState(T msg) {
		queue.add(msg);
		notifyObservers();
	}

	@Override
	protected T getState() {
		return queue.getLast();
	}
}
