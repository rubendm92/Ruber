package ruber.core.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable {

    private final List<Observer> observerList;

    protected Observable() {
        this.observerList = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    public void removeObserver(Observer observer) {
        observerList.remove(observer);
    }

    protected void notifyChanges() {
        observerList.forEach(Observer::changed);
    }
}
