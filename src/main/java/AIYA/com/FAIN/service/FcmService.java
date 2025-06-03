package AIYA.com.FAIN.service;

import AIYA.com.FAIN.entity.FcmToken;
import AIYA.com.FAIN.repository.FcmTokenRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

// 실제로 FCM 푸시 알림을 보내는 서비스
@Service
@RequiredArgsConstructor
public class FcmService {

  private final FcmTokenRepository fcmTokenRepository;

  public void sendMessage(String userId, Long reportId) {

    List<FcmToken> tokens = fcmTokenRepository.findAllByUserId(userId);

    if(tokens.isEmpty()){
      throw new IllegalArgumentException("해당 유저의 FCM토큰이 존재하지 않습니다.");
    }

    // 유저의 모든 토큰에대해 메시지 생성 및 FCM서버로 전송
    for(FcmToken token : tokens){
      Message message = Message.builder().setToken(token.getToken())
          .setNotification(Notification.builder()
              .setBody("낙상이 감지되었습니다.")
              .build())
          .putData("reportId",reportId.toString()).build();

      try{
        FirebaseMessaging.getInstance().send(message);
      }catch (Exception e){
        e.printStackTrace();
      }
    }

  }
  //중복검사하여 토큰 저장
  public void registerToken(String userId, String token){
    boolean exists = fcmTokenRepository.findByUserIdAndToken(userId, token).isPresent();
    if(!exists){
      FcmToken fcmToken = new FcmToken(userId, token);
      fcmTokenRepository.save(fcmToken);
    }
  }
}
