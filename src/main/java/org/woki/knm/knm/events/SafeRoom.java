package org.woki.knm.knm.events;

public class SafeRoom implements Event {

    @Override
    public void playEvent() {
        System.out.println("Не смотря на мрачность, комната выглядит безопасной");
    }
}
