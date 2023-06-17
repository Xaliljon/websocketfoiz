import 'dart:io';
import 'package:web_socket_channel/io.dart';

void main() {
  final channel = IOWebSocketChannel.connect('ws://localhost:8080');
  channel.stream.listen((message) {
    print('Serverdan kelgan raqam: $message');
    if(message=="5") {
      channel.sink.close();
    }
  });

  channel.sink.add('raqam');
}
