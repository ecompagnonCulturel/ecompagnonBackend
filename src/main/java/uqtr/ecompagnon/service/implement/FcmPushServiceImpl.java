package uqtr.ecompagnon.service.implement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import uqtr.ecompagnon.dto.FcmRequestDTO;

import java.util.HashMap;
import java.util.Map;


@Service
public class FcmPushServiceImpl {
    private Logger logger = LoggerFactory.getLogger(FcmPushServiceImpl.class);

    private FCMServiceImpl fcmService;

    public FcmPushServiceImpl(FCMServiceImpl fcmService) {
        this.fcmService = fcmService;
    }


    public void sendMessageWithoutDataToToken(FcmRequestDTO request) {
        try {
            fcmService.sendMessageWithoutDataToToken(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void sendPushNotificationWithoutDataToTopic(FcmRequestDTO request) {
        try {
            fcmService.sendMessageWithoutDataToTopic(request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }

    public void sendMessageWithDataToTopic(FcmRequestDTO request) {
        try {
            fcmService.sendMessageWithDataToTopic(getPayloadDataQuest(request.getNbreQuest(),request.getTypes()), request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    public void sendMessageWithDataToToken(FcmRequestDTO request) {
        try {
            //System.out.println(request.getUserId());
            fcmService.sendMessageWithDataToToken(getPayloadDataCreateAccount(request.getUserId()), request);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }


    private Map<String, String> getPayloadDataQuest(Integer nbreQuest,String types) {
        Map<String, String> pushData = new HashMap<>();
        pushData.put("NombreQuest", nbreQuest.toString());
        pushData.put("notification_foreground","true") ;
        pushData.put("Types", types);
        return pushData;
    }
    private Map<String, String> getPayloadDataCreateAccount(Integer userId) {
        Map<String, String> pushData = new HashMap<>();
        pushData.put("userId", userId.toString());
        pushData.put("notification_foreground","true") ;

        return pushData;
    }
}
