package input.utils;

import java.util.Arrays;

/**
 *
 * @author aglassman
 */
public class Sequence
{
    public final String name;
    public final Keystroke[] sequence;
    public Sequence(String name,Keystroke... sequence)
    {
        this.name = name;
        this.sequence = sequence;
    }

    @Override
    public String toString()
    {
        return String.format("SequenceName: %s, Sequence: [%s]",name,Arrays.toString(sequence));
    }

    String getState() {
        StringBuilder sb = new StringBuilder();
        for(Keystroke kst : sequence)
        {
            sb.append(String.format("(name:%s state: %s) ",kst.key, kst.currentValue));
        }
        return sb.toString();
    }
}
