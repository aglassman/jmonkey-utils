package input.utils;

/**
 *
 * @author aglassman
 */
public class Keystroke
{
    public final int key;
    public final Type sequenceType;
    int currentValue = 0;
    public Keystroke(int key, Type sequenceType)
    {
        this.key = key;
        this.sequenceType = sequenceType;
    }

    @Override
    public String toString()
    {
        return Integer.toString(key);
    }
    
    public enum Type
    {
        HELD,PRESSED,TERMINAL
    }
}
