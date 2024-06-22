## 프로젝트 구성
 
- lg-was-admin 
  - WAS API 서버

## 프로젝트 실행

- 디비 접근 가능 상태 처리 (AWS 방화벽 설정)
- querydsl 사용을 위한 qclass 생성

## 빌드 및 배포 방법

- lg-was-admin package 수행 후 jar 파일 생성
- 해당 jar 파일 FTP(파일 질라)를 통하여 서버에 배포 (위치 : /opt/lawgg-admin, 파일명: lawgg-admin.jar)
- 해당 서비스 실행 (명령어 : systemctl restart lawgg-admin.service ) - 실행 스크립트 사전 작성
- 로그 수행 확인 (위치 : /opt/lawgg-admin/logs, 파일명: lawgg-admin.log)
- 주의사항: jar 교체시 백업 jar로 이전 jar 파일명 수정변경 (ex 파일명.20231102.jar)

## 기타

#### 시스템 서비스 등록 스크립트 경로 (/etc/systemd/system/lawgg-admin.service)
#### 시스템 서비스 수행 스크립트 경로 (/opt/lawgg-admin/lawgg-admin.conf)