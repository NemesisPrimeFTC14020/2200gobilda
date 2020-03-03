package org.firstinspires.ftc.teamcode;

import android.content.Context;
import com.qualcomm.ftccommon.SoundPlayer;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class Sounds {
    /*Sound choices: {"ss_alarm", "ss_bb8_down", "ss_bb8_up", "ss_darth_vader", "ss_fly_by",
            "ss_mf_fail", "ss_laser", "ss_laser_burst", "ss_light_saber", "ss_light_saber_long", "ss_light_saber_short",
            "ss_light_speed", "ss_mine", "ss_power_up", "ss_r2d2_up", "ss_roger_roger", "ss_siren", "ss_wookie"} */

    private SoundPlayer.PlaySoundParams params = new SoundPlayer.PlaySoundParams();
    String[] playing = new String[0];

    public void playSound(String sound, HardwareMap hw, float volume, boolean wait, int loopControl, float rate) {

        Context app = hw.appContext;
        int soundID = -1;
        params.volume = volume;
        params.waitForNonLoopingSoundsToFinish = wait;
        params.loopControl = loopControl;
        params.rate = rate;

        if ((soundID = app.getResources().getIdentifier(sound, "raw", app.getPackageName())) != 0) {
                playing = add(playing, sound);
            SoundPlayer.getInstance().startPlaying(app, soundID, params, null, new Runnable() {
                public void run() {
                    playing = remove(playing, sound);
                }});
        }
        else throw new IllegalArgumentException();
    }

    private String[] add(String[] array, String item) {
        String[] newarray = new String[array.length + 1];
        for (int i = array.length - 1; i >= 0; i--) {
            newarray[i] = array[i];
        }
        newarray[array.length] = item;
        return newarray;
    }
    private String[] remove(String[] array, String item) {
        String[] newarray = new String[array.length - 1];
        int index = search(array, item);
        if (index == -1) throw new IllegalArgumentException();
        for (int i = index - 1; i >= 0; i--) {
            newarray[i] = array[i];
        }
        for (int i = array.length - 1; i > index; i--) {
            newarray[i-1] = array[i];
        }
        return newarray;
    }
    private int search(String[] array, String item) {
        for (int i = array.length - 1; i >= 0; i--) {
            if (array[i] == item) return i;
        }
        return -1;
    }
    public boolean isPlaying(String item) {
        int result = search(playing, item);
        return !(result == -1);
    }
}
