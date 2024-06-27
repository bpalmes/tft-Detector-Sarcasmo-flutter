import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

const MethodChannel _channel = MethodChannel('com.example.sarcasmo/accessibility');

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({super.key, required this.title});

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  String _screenText = 'No text yet';
  double _polarity = 0.0;

  @override
  void initState() {
    super.initState();
    _getTextFromScreen();
  }

  Future<void> _getTextFromScreen() async {
    String text;
    try {
      text = await _channel.invokeMethod('getTextFromScreen');
      setState(() {
        _screenText = text;
        _polarity = _analyzeText(text);
      });
    } on PlatformException catch (e) {
      text = "Failed to get text: '${e.message}'.";
    }

    setState(() {
      _screenText = text;
    });
  }

  double _analyzeText(String text) {
    // Aquí puedes implementar tu lógica para analizar el texto y calcular la polaridad
    // Por simplicidad, se devuelve un valor aleatorio entre -1 y 1
    return (text.length % 2 == 0) ? 1.0 : -1.0;
  }

  @override
  Widget build(BuildContext context) {
    Color polarityColor = _polarity >= 0 ? Colors.green : Colors.red;
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: <Widget>[
            const Text(
              'Text on screen:',
            ),
            Text(
              _screenText,
              style: Theme.of(context).textTheme.headlineMedium,
            ),
            const SizedBox(height: 20),
            Container(
              width: 50,
              height: 50,
              decoration: BoxDecoration(
                color: polarityColor,
                shape: BoxShape.circle,
              ),
              child: Center(
                child: Text(
                  _polarity.toStringAsFixed(1),
                  style: const TextStyle(color: Colors.white),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}
