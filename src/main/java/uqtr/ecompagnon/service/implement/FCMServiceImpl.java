package uqtr.ecompagnon.service.implement;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.firebase.messaging.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import uqtr.ecompagnon.dto.FcmRequestDTO;
import uqtr.ecompagnon.dto.NotificationParameterDTO;


@Service

public class FCMServiceImpl {
    private Logger logger = LoggerFactory.getLogger(FCMServiceImpl.class);


    public void sendMessageWithoutDataToToken(FcmRequestDTO request)
                        throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithoutDataToToken(request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response+ " msg "+jsonOutput);
    }

    public void sendMessageWithoutDataToTopic(FcmRequestDTO request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithoutDataToTopic(request);
        String response = sendAndGetResponse(message);
        logger.info("Sent message without data. Topic: " + request.getTopic() + ", " + response);
    }
    public void sendMessageWithDataToToken(Map<String, String> data, FcmRequestDTO request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithDataToToken(data, request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message with data. Topic: " + request.getTopic() + ", " + response+ " msg "+jsonOutput);
    }

    public void sendMessageWithDataToTopic(Map<String, String> data, FcmRequestDTO request)
            throws InterruptedException, ExecutionException {
        Message message = getPreconfiguredMessageWithDataToTopic(data, request);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(message);
        String response = sendAndGetResponse(message);
        logger.info("Sent message with data. Topic: " + request.getTopic() + ", " + response+ " msg "+jsonOutput);
    }

    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
        return FirebaseMessaging.getInstance().sendAsync(message).get();
    }

    private Message getPreconfiguredMessageWithoutDataToToken(FcmRequestDTO request) {
        return getPreconfiguredMessageBuilder(request).setToken(request.getToken())
                .build();
    }
    private Message getPreconfiguredMessageWithDataToToken(Map<String, String> data, FcmRequestDTO request) {
        return getPreconfiguredMessageBuilder(request).putAllData(data).setToken(request.getToken())
                .build();
    }
    private Message getPreconfiguredMessageWithoutDataToTopic(FcmRequestDTO request) {
        return getPreconfiguredMessageBuilder(request).setTopic(request.getTopic())
                .build();
    }

    private Message getPreconfiguredMessageWithDataToTopic(Map<String, String> data, FcmRequestDTO request) {
        return getPreconfiguredMessageBuilder(request).putAllData(data).setTopic(request.getTopic())
                .build();
    }


    private Message.Builder getPreconfiguredMessageBuilder(FcmRequestDTO request) {
        System.out.println(request.getColor());
        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
        WebpushConfig webpushConfig=getWebpushConfig(request.getTopic());
        return Message.builder()
                .setApnsConfig(apnsConfig).setAndroidConfig(androidConfig).setWebpushConfig(webpushConfig).setNotification(
                        new Notification(request.getTitle(), request.getMessage()));
    }

    private AndroidConfig getAndroidConfig(String topic,String color) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder()
                        .setColor(color).setTag(topic).build()).build();
    }

    private AndroidConfig getAndroidConfig(String topic) {
        return AndroidConfig.builder()
                .setTtl(Duration.ofMinutes(2).toMillis()).setCollapseKey(topic)
                .setPriority(AndroidConfig.Priority.HIGH)
                .setNotification(AndroidNotification.builder().setSound(NotificationParameterDTO.SOUND.getValue())
                        .setColor(NotificationParameterDTO.COLOR.getValue()).setTag(topic). build()).build();
    }



    private WebpushConfig getWebpushConfig(String topic) {
        return WebpushConfig.builder()
                .setNotification(WebpushNotification.builder().setTag(topic).build()).build();
    }

    private ApnsConfig getApnsConfig(String topic) {
        return ApnsConfig.builder()
                .setAps(Aps.builder().setContentAvailable(true). setCategory(topic).setThreadId(topic).setBadge(1).build()).build();
    }



}
