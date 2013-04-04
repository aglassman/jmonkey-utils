package input.utils;

import com.jme3.input.InputManager;
import com.jme3.input.KeyInput;
import java.util.Arrays;

/**
 *
 * @author aglassman
 */
public class Examples {
        public static void main(String[] args)
        {
            //would need a real instance of InputManager from an applicaiton.
            InputManager im = new InputManager(null, null, null, null);

            Sequence keySequence = new Sequence("Key Sequence 1.", 
                    new Keystroke(KeyInput.KEY_LCONTROL, Keystroke.Type.HELD),
                    new Keystroke(KeyInput.KEY_Z, Keystroke.Type.TERMINAL));

            KeystrokeSequence ks = new KeystrokeSequence(im, Arrays.asList(keySequence));

            ks.callbacks.add(new KeystrokeSequence.Callback() {

                @Override
                public void run() {
                    //code to run
                }
            });
            
            ks.callbacks.add(new KeystrokeSequence.Callback() {

                @Override
                public void run() {
                    //another callback to run
                }
            });

            Sequence keySequence2 = new Sequence("Key Sequence 1.", 
                    new Keystroke(KeyInput.KEY_LCONTROL, Keystroke.Type.HELD),
                    new Keystroke(KeyInput.KEY_X, Keystroke.Type.HELD),
                    new Keystroke(KeyInput.KEY_1, Keystroke.Type.PRESSED),
                    new Keystroke(KeyInput.KEY_2, Keystroke.Type.PRESSED),
                    new Keystroke(KeyInput.KEY_RETURN, Keystroke.Type.TERMINAL));

            KeystrokeSequence ks2 = new KeystrokeSequence(im, Arrays.asList(keySequence2));

            ks2.callbacks.add(new KeystrokeSequence.Callback() {

                @Override
                public void run() {
                    //code to run
                }
            });
            
        }
}
