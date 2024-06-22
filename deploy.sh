#!/bin/bash

# lg-admin 서비스의 상태를 확인
service_status=$(systemctl is-active lg-admin)

# lg-admin 서비스가 실행 중인 경우
if [ "$service_status" == "active" ]; then
    # lg-admin 서비스를 중지
    systemctl stop lg-admin
else
    echo "lg-admin 서비스는 이미 중지되어 있습니다."
fi

cd /home/sp/deploy/admin

sleep 1

cp /home/sp/source/admin/target/*.jar app.jar

rm -rf /home/sp/source/admin/target

# lg-server 서비스를 시작
systemctl start lg-admin
echo "lg-server 서비스를 시작했습니다."