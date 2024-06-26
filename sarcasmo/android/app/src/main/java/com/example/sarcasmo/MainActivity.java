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
                                // Implementar la lógica para obtener el texto de la pantalla
                                String text = getTextFromScreen();
                                result.success(text);
                            } else {
                                result.notImplemented();
                            }
                        }
                );
    }

    private String getTextFromScreen() {
        // Implementar la lógica para obtener el texto de la pantalla
        return "Texto de ejemplo"; // Reemplazar con el texto real obtenido del servicio de accesibilidad
    }
}
