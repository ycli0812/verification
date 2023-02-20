package info;

import java.util.ArrayList;

enum InfoType {
    INFO,
    WARNING,
    ERROR
}

public class Info {
    ArrayList<String> involvedElementIds;
    String formatString = "This is an output info.";
    InfoType type;

    public Info(String text, InfoType type) {
        this.formatString = text;
        this.type = type;
    }
}
