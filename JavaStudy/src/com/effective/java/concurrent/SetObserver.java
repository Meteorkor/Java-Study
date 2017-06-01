
package com.effective.java.concurrent;

public interface SetObserver<E> {
    public void added(ObservableSet<E> s, E e);
}
