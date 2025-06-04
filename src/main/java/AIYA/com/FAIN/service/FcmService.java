package AIYA.com.FAIN.service;

import AIYA.com.FAIN.entity.FcmToken;
import AIYA.com.FAIN.entity.Users;
import AIYA.com.FAIN.repository.FcmTokenRepository;
import AIYA.com.FAIN.repository.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
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
  private final UserRepository userRepository;

  public void sendMessage(String userId, Long reportId) {

    //유저아이디로 유저 엔티티 가져오기
    Users user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

    //fcm token 모두 찾기
    List<FcmToken> tokens = fcmTokenRepository.findAllByUser(user);


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

//      try{
//        FirebaseMessaging.getInstance().send(message);
//      }catch (Exception e){
//        e.printStackTrace();
//      }
      try {
        FirebaseMessaging.getInstance().send(message);
      } catch (FirebaseMessagingException e) {
        String errMsg = e.getMessage();
        System.err.println("FCM 전송 실패 - 토큰: " + token.getToken());
        e.printStackTrace();

        // 유효하지 않은 FCM 토큰이면 삭제해도 됨 (선택)
        if ("INVALID_ARGUMENT".equals(e.getMessagingErrorCode().name())) {
          System.err.println("❌ 유효하지 않은 토큰 → DB에서 삭제");
          fcmTokenRepository.delete(token); // optional
        }
      } catch (Exception e) {
        System.err.println("FCM 전송 중 알 수 없는 에러 발생");
        e.printStackTrace();
      }

    }

  }
  //중복검사하여 토큰 저장
  public void registerToken(String userId, String token) {
    Users user = userRepository.findByUserId(userId)
        .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

    // 기존 해당 유저의 모든 토큰 삭제 (1개만 유지할 거니까)
    fcmTokenRepository.deleteAllByUser(user);

    // 새 토큰 저장
    FcmToken fcmToken = new FcmToken(user, token);
    fcmTokenRepository.save(fcmToken);
  }
}
