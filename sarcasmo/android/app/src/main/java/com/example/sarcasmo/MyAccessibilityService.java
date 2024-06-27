package com.example.sarcasmo;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;
import android.util.Log;
import io.flutter.plugin.common.MethodChannel;

public class MyAccessibilityService extends AccessibilityService {

    private static final String TAG = "MyAccessibilityService";
    private static MethodChannel.Result result;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {
        if (event.getEventType() == AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED) {
            CharSequence packageName = event.getPackageName();
            CharSequence className = event.getClassName();
            CharSequence text = event.getText();
            Log.d(TAG, "Package: " + packageName + ", Class: " + className + ", Text: " + text);

            // Enviar el texto al método canalizado en Flutter
            if (result != null && text != null) {
                result.success(text.toString());
            }
        }
    }

    @Override
    public void onInterrupt() {
        // Implementar lógica para interrupciones
    }

    public static void setResult(MethodChannel.Result res) {
        result = res;
    }
}
