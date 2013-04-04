package input.utils;

import com.jme3.input.InputManager;
import com.jme3.input.controls.ActionListener;
import com.jme3.input.controls.KeyTrigger;
import input.utils.Keystroke.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aglassman
 */
public class KeystrokeSequence {
    
    public final List<Callback> callbacks = new ArrayList<Callback>();
    
    public KeystrokeSequence(InputManager im, List<Sequence> inputSequences)
    {

        for(final Sequence ks : inputSequences)
        {
            String[] mappingNames = new String[ks.sequence.length];
            for(int index = 0; index < ks.sequence.length; index++)
            {
                im.addMapping(ks.sequence[index].toString(), new KeyTrigger(ks.sequence[index].key));
                mappingNames[index] = ks.sequence[index].toString();
            }
           
            im.addListener(new ActionListener() {

            @Override
            public void onAction(String name, boolean isPressed, float tpf) {
               for(Keystroke kst : ks.sequence)
                {
                    if(kst.currentValue == 0 && !Integer.toString(kst.key).equals(name))
                    {
                        //A previous key was not activated, sequence failed.
                        break;
                    }
                    
                    //If key pressed is current key, do some evaluation
                    if(Integer.toString(kst.key).equals(name))
                    {
                        //If it is pressed, and is of hold type, set to one and continue
                        if(isPressed && kst.sequenceType.equals(Type.HELD))
                        {
                           kst.currentValue = 1;
                            break;
                        }
                        
                        //If it is released and is of held type, no longer valid, break.
                        if(!isPressed && kst.sequenceType.equals(Type.HELD))
                        {
                           resetSequence(ks);
                            break;
                        }
                        
                        //If it is pressed, yet already set, fail.
                        if(isPressed && kst.currentValue == 1 && kst.sequenceType.equals(Type.PRESSED))
                        {
                            resetSequence(ks);
                            break;
                        }
                        
                        //If it is released, and type was pressed, set to valid.
                        if(!isPressed && kst.sequenceType.equals(Type.PRESSED))
                        {
                            kst.currentValue = 1;
                            break;
                        }
                        
                        //If it is pressed, and is a terminal, and it made it this far, 
                        // the sequence was completed correctly.
                        if(isPressed && kst.sequenceType.equals(Type.TERMINAL))
                        {
                            resetSequenceAndRunCallback(ks);
                            break;
                        }
                    }
                }
                
            }
            },  mappingNames);
            
            
        }
        
    }
    
    public void resetSequenceAndRunCallback(Sequence ks)
    {
        for(Callback c: callbacks)
            c.run();
        
        resetSequence(ks);
        
    }
    
    public void resetSequence(Sequence ks)
    {
        for(Keystroke kst : ks.sequence)
            kst.currentValue = 0;
    }
           
    public interface Callback
    {
        public void run();
    }
    

}
