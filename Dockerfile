# 1. OpenJDK 21 기반 이미지 사용
FROM openjdk:21-jdk-slim

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. 빌드된 .jar파일 복사
COPY build/libs/FAIN-0.0.1-SNAPSHOT.jar app.jar

# 4. 실행 명령어 - prod 프로파일 활성화
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-Duser.timezone=Asia/Seoul", "-jar", "app.jar"]