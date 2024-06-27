package com.example.sarcasmo;

import android.os.Bundle;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.plugin.common.MethodChannel;

public class MainActivity extends FlutterActivity {
    private static final String CHANNEL = "com.example.sarcasmo/accessibility";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new MethodChannel(getFlutterEngine().getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {
                            if (call.method.equals("getTextFromScreen")) {
                                // Configurar el resultado para ser enviado desde el servicio de accesibilidad
                                MyAccessibilityService.setResult(result);
                            } else {
                                result.notImplemented();
                            }
                        }
                );
    }
}
