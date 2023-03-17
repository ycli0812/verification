package info;

import org.jetbrains.annotations.NotNull;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

/**
 * An output generated during the verification.
 *
 * @author Lyc
 * @version 2023.02.20
 */
public class Info {
    ArrayList<String> involvedElementIds;
    String formatString = "This is an output info.";
    InfoType type;

    public Info(InfoType type, String ...elements) {
//        this.formatString = text;
        this.involvedElementIds = new ArrayList<String>();
        this.type = type;
        for(String id : elements) {
            this.involvedElementIds.add(id);
        }
    }

    /**
     * Construct an Info with text.
     *
     * @param format Text of the Info
     * @param type Type, see InfoType for possible options
     * @param elements What elements are involved
     */
    public Info(String format, InfoType type, String ...elements) {
        this(type, elements);
        this.formatString = format;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s", this.type, this.formatString);
    }
}
