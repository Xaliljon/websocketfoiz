import 'dart:io';
import 'dart:async';
import 'package:shelf/shelf_io.dart' as shelf_io;
import 'package:shelf_web_socket/shelf_web_socket.dart';

void main() async {
  final server = await shelf_io.serve(
    webSocketHandler((webSocket) {
      Timer.periodic(Duration(seconds: 1), (timer) {
        webSocket.sink.add(timer.tick.toString());
      });
    }),
    InternetAddress("127.0.0.1"),8000
  );
  print('WebSocket server ishga tushdi: ${server.address}:${server.port}');
}
