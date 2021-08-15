package ru.sa.hooks;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

public class HookRegistrator {
    public static void registerHooks() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            System.err.println("There was a problem registering the native hook.");
            e.printStackTrace();

            System.exit(1);
        }

        GlobalScreen.addNativeKeyListener(new GlobalKeyListenerExample());
    }

    public static void main(String[] args) {
        HookRegistrator.registerHooks();
    }
}
