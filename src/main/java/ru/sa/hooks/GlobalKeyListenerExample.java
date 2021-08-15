package ru.sa.hooks;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class GlobalKeyListenerExample implements NativeKeyListener {

    public void nativeKeyPressed(NativeKeyEvent event) {
        long timer_f = 0;
        long timer_d = 5 * 1000;

        System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(event.getKeyCode()));
        try {

            if (event.getKeyCode() == NativeKeyEvent.VC_F12) {
                GlobalScreen.unregisterNativeHook();
            }
            //todo timer

            if (event.getKeyCode() == NativeKeyEvent.VC_R) {
                timer_f = timerSetTime(timer_d);

            }

            if (timer_f >= System.currentTimeMillis()) {
                pressRkey();//todo исправить нажатие
                timer_f = timerSetTime(timer_d);
            }
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
    }

    public void nativeKeyReleased(NativeKeyEvent e) {
        System.out.println("Key Released: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent e) {
        System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(e.getKeyCode()));
    }

    public long timerSetTime(long timer_d) {
        long timer_f = 0;
        long timer_s = 0;
        timer_s = System.currentTimeMillis();
        timer_f = timer_s + timer_d;
        return timer_f;
    }

    public void pressRkey() {
        System.out.println("Нажатие");
    }
}