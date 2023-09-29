package org.woki.knm.events;

import java.util.ArrayList;
import java.util.List;

public interface Event {
    List<String> gameMessages = new ArrayList<>();

    List<String> playEvent();
}
