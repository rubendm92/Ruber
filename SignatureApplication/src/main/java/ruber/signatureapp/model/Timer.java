package ruber.signatureapp.model;

import java.util.TimerTask;

public class Timer {

    private java.util.Timer timer;
    private final Function function;
    private final int interval;

    public Timer(int interval, Function function) {
        this.function = function;
        this.interval = interval;
    }

    public void restart() {
        stop();
        start();
    }

    public void stop() {
        if (timer == null) return;
        timer.cancel();
        timer = null;
    }

    public void start() {
        if (timer != null) return;
        timer = new java.util.Timer();
        timer.scheduleAtFixedRate(getTimerTask(), interval, interval);
    }

    private TimerTask getTimerTask() {
        return new TimerTask() {

            @Override
            public void run() {
                function.apply();
            }
        };
    }

    @FunctionalInterface
    public interface Function {
        public void apply();
    }
}