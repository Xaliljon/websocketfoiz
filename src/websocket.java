import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Random;


@ServerEndpoint("/random")
public class RandomNumberWebSocket {

    @OnOpen
    public void onOpen(Session session) {
        System.out.println("WebSocket connection opened: " + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received message from client: " + message);
    }

    @OnClose
    public void onClose(Session session) {
        System.out.println("WebSocket connection closed: " + session.getId());
    }

    @OnError
    public void onError(Throwable error) {
        System.err.println("WebSocket error: " + error.getMessage());
    }

    @Scheduled(fixedDelay = 1000) // Bu qatorni yozish uchun `@EnableScheduling` annotatsiyasini kiritishingiz kerak
    public void sendRandomNumber(Session session) {
        if (session.isOpen()) {
            int randomNumber = new Random().nextInt(100);
            try {
                session.getBasicRemote().sendText(String.valueOf(randomNumber));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

