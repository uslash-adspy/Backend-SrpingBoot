# 1단계: 빌드 환경 설정 (멀티 스테이지 빌드)
# Eclipse Temurin은 안정적인 OpenJDK 배포판입니다.
FROM eclipse-temurin:21-jdk-jammy as builder

# 작업 디렉토리 설정
WORKDIR /app

# Gradle Wrapper 파일과 설정 파일 복사 (캐싱을 위해 먼저 복사)
# build.gradle이 변경되지 않으면 종속성 다운로드 단계를 캐싱하여 빌드 속도를 높일 수 있습니다.
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle settings.gradle ./

# 소스 코드 복사
COPY src/ ./src/

# 권한 부여 (Windows에서는 이미 실행 가능하더라도 Linux 컨테이너에서는 필요할 수 있음)
RUN chmod +x gradlew

# 애플리케이션 빌드
# -x test: 테스트는 건너뜁니다. Docker 빌드 시 테스트는 별도로 실행하는 경우가 많습니다.
RUN ./gradlew clean build -x test

# 2단계: 런타임 환경 설정
# 더 작고 가벼운 JRE 이미지를 사용하여 최종 이미지 크기를 줄입니다.
FROM eclipse-temurin:21-jre-jammy

# 작업 디렉토리 설정
WORKDIR /app

# 빌드 단계에서 생성된 JAR 파일 복사
# build/libs 디렉토리에서 JAR 파일을 찾아 app.jar로 복사합니다.
# 실제 JAR 파일명 패턴에 맞게 조정해야 할 수 있습니다 (예: *.jar 또는 my-app-*.jar)
COPY --from=builder /adSpy/build/libs/*.jar app.jar

# Spring Boot 애플리케이션이 사용할 포트 명시 (application.properties 또는 application.yml에 설정된 포트와 일치해야 함)
EXPOSE 8088

# 컨테이너 시작 시 실행될 명령어
# 일반적으로 JVM 메모리 옵션 등은 ENTRYPOINT에 포함하지 않고 JAVA_OPTS 환경 변수로 전달합니다.
ENTRYPOINT ["java", "-jar", "app.jar"]