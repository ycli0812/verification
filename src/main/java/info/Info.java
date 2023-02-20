package info;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Info {
    ArrayList<String> involvedElementIds;
    String formatString = "This is an output info.";
    InfoType type;

    public Info(InfoType type, String ...elements) {
//        this.formatString = text;
        this.type = type;
        for(String id : elements) {
            this.involvedElementIds.add(id);
        }
    }
    
    public Info(String format, InfoType type, String ...elements) {
        this(type, elements);
        this.formatString = format;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.type, this.formatString);
    }
}
