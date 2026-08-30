package nl.svsticky;

import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import org.keycloak.events.admin.AdminEvent;
import org.keycloak.models.KeycloakSession;

public class EmailUpdateListener implements EventListenerProvider {
    private final String webhookUrl;
    private final String webhookSecret;

    public EmailUpdateListener(String webhookSecret, String webhookUrl) {
        this.webhookUrl = webhookUrl;
        this.webhookSecret = webhookSecret;
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType() == EventType.UPDATE_EMAIL) {
            try {
                String userId = event.getUserId();

                URL url = new URL(webhookUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                conn.setRequestProperty("X-Webhook-Secret", webhookSecret);

                String body = "\"" + userId + "\"";

                try (OutputStream os = conn.getOutputStream()) {
                    os.write(body.getBytes());
                }

                int responseCode = conn.getResponseCode();
                System.out.println("Webhook sent, response: " + responseCode);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void close() {}

    @Override
    public void onEvent(AdminEvent adminEvent, boolean includeRepresentation) {
    }
}
