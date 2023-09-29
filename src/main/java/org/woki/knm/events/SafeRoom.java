package org.woki.knm.events;

import java.util.List;

public class SafeRoom implements Event {

    @Override
    public List<String> playEvent() {
        gameMessages.add("Не смотря на мрачность, комната выглядит безопасной");

        return gameMessages;
    }
}
