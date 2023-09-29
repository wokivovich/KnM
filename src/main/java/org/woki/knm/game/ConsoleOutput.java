package org.woki.knm.game;

import java.util.List;

public class ConsoleOutput implements View {
    private static final String NEW_LINE = "\n";

    public String getGameView(List<String> messages) {
        StringBuilder sb = new StringBuilder();
        messages.forEach(message -> sb.append(NEW_LINE + message + NEW_LINE));
        System.out.println(sb);

        return sb.toString();
    }
}
