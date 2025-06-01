package AIYA.com.FAIN.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.IOException;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirebaseConfig {

  @PostConstruct
  public void initializeFirebase(){
    try{
      // json의 서비스 계정 키 파일 불러오기
      FileInputStream serviceAccount = new FileInputStream(System.getenv("FCM_KEY_PATH"));
      // 그 키로 Firebase와 통신할 수 있는 인증 옵션 설정
      FirebaseOptions options = FirebaseOptions.builder().setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

      // 초기화 되어 있지 않으면 초기화하여 계정 인증
      if (FirebaseApp.getApps().isEmpty()){
        FirebaseApp.initializeApp(options);
        System.out.println("초기화 성공");
      }

    } catch (IOException e) {
      System.err.println("초기화 실패"+e.getMessage());
    }
  }

}
