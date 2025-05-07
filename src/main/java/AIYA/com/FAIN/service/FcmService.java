package AIYA.com.FAIN.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.stereotype.Service;

// 실제로 FCM 푸시 알림을 보내는 서비스
@Service
public class FcmService {

  public void sendMessage(String targetToken){
    Notification notification = Notification.builder().setBody("낙상이 감지되었습니다").build();
    Message message = Message.builder().setToken(targetToken).setNotification(notification).build();

    try{
      String response = FirebaseMessaging.getInstance().send(message);
      System.out.println("푸시 알림 전송 완료: " + response);
    } catch (FirebaseMessagingException e) {
      System.out.println("푸시 알림 전송 실패: " + e);
    }
  }

}
