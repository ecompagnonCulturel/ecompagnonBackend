package uqtr.ecompagnon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uqtr.ecompagnon.dto.FcmRequestDTO;
import uqtr.ecompagnon.dto.FcmResponseDTO;
import uqtr.ecompagnon.service.implement.FcmPushServiceImpl;
@RestController
@RequestMapping("/api/notification")
public class FcmController {
    private FcmPushServiceImpl fcmPushService;

    public FcmController(FcmPushServiceImpl fcmPushService) {
        this.fcmPushService = fcmPushService;
    }

    @PostMapping("/topic")
    public ResponseEntity sendPushNotificationWithoutDataToTopic(@RequestBody FcmRequestDTO request) {
        fcmPushService.sendPushNotificationWithoutDataToTopic(request);
        return new ResponseEntity<>(new FcmResponseDTO(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/token")
    public ResponseEntity sendMessageWithoutDataToToken(@RequestBody FcmRequestDTO request) {
        fcmPushService.sendMessageWithoutDataToToken(request);
        return new ResponseEntity<>(new FcmResponseDTO(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/topicdata")
    public ResponseEntity sendMessageWithDataToTopic(@RequestBody FcmRequestDTO request) {
        fcmPushService.sendMessageWithDataToTopic(request);
        return new ResponseEntity<>(new FcmResponseDTO(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

    @PostMapping("/tokendata")
    public ResponseEntity sendMessageWithDataToToken(@RequestBody FcmRequestDTO request) {
        fcmPushService.sendMessageWithDataToToken(request);
        return new ResponseEntity<>(new FcmResponseDTO(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }

  /*  @PostMapping("/data")
    public ResponseEntity sendDataNotification(@RequestBody FcmRequest request) {
        fcmPushService.sendPushNotification(request);
        return new ResponseEntity<>(new FcmResponse(HttpStatus.OK.value(), "Notification has been sent."), HttpStatus.OK);
    }*/
}
