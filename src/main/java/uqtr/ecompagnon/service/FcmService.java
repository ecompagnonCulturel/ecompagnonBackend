package uqtr.ecompagnon.service;

import uqtr.ecompagnon.dto.NotificationRequestDTO;
import uqtr.ecompagnon.dto.SubscriptionRequestDto;

public interface FcmService {
     void subscribeToTopic(SubscriptionRequestDto subscriptionRequestDto);
     void unsubscribeFromTopic(SubscriptionRequestDto subscriptionRequestDto);
     String sendPnsToTopic(NotificationRequestDTO notificationRequestDto);
   //  void initialize();
     String sendPnsToDevice(NotificationRequestDTO notificationRequestDto);
}
