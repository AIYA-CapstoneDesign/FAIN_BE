package AIYA.com.FAIN.service;

import AIYA.com.FAIN.entity.FcmToken;
import AIYA.com.FAIN.repository.FcmTokenRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 실제로 FCM 푸시 알림을 보내는 서비스
@Service
@RequiredArgsConstructor
public class FcmService {

  private final FcmTokenRepository fcmTokenRepository;

  public void sendMessage(String userId, Long reportId) {

    // 유저의 fcm 토큰 찾기
    FcmToken fcmToken = fcmTokenRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException("FCM 토큰을 찾을 수 없음"));

    // 메시지 생성
    Message message = Message.builder()
        .setToken(fcmToken.getToken())
        .setNotification(Notification.builder().setBody("낙상이 감지되었습니다.")  // 사용자용 메세지
        .build()).putData("reportId", reportId.toString())
        .build();

    // fcm 서버로 메시지 전송
    try {
      String response = FirebaseMessaging.getInstance().send(message);
      System.out.println("알림 전송 성공: " + response);
    } catch (Exception e) {
      System.out.println("알림 전송 실패: " + e.getMessage());
    }

  }
}
