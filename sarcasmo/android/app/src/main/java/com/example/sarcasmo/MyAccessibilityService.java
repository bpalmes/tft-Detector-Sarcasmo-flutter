package com.example.sarcasmo;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.util.Log;

public class MyAccessibilityService extends AccessibilityService {

    private static final String TAG = "MyAccessibilityService";

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            CharSequence packageName = event.getPackageName();
            CharSequence className = event.getClassName();
            Log.d(TAG, "Package: " + packageName + ", Class: " + className);

            // Aquí puedes obtener el texto del evento y hacer algo con él
        }
    }

    @Override
    public void onInterrupt() {
        // Implementar lógica para interrupciones
    }
}
