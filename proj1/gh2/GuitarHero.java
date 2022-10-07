package gh2;

import deque.ArrayDeque;
import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

/**
 * A client that uses the synthesizer package to replicate a plucked guitar string sound
 */
public class GuitarHero {
    public static final double CONCERT = 440.0;

    public static void main(String[] args) {
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        ArrayDeque<GuitarString> keyarray = new ArrayDeque<>();
        double pitch;
        for (int i = 0; i < keyboard.length(); i++) {
            pitch = CONCERT * Math.pow(2, (i - 24) / 12.0);
            GuitarString StringSounds = new GuitarString(pitch);
            keyarray.addLast(StringSounds);
        }

        while (true) {
            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int key_idx = keyboard.indexOf(key);
                if (key_idx < 37 && key_idx >= 0) {
                    keyarray.get(key_idx).pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < keyboard.length(); i += 1) {
                sample += keyarray.get(i).sample();
            }

            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < keyboard.length(); i += 1) {
                keyarray.get(i).tic();
            }
        }
    }
}
