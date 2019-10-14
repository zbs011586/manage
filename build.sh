#!/bin/bash
echo '自动部署项目脚本...'
echo '1. 拉取github代码...'
git pull
echo '2. 检查9999端口是否被占用...'
pid_blog=`lsof -i :9999|grep -v "PID"|awk '{print $2}'`
if [ "$pid_blog" != "" ];
then
    echo '9999端口被占用'
    echo $pid_blog
    kill -9 "$pid_blog"
    echo $pid_blog '进程已被杀死'
else
    echo "端口未被占用"
fi
echo '3. 清理原有项目...'
mvn clean
echo '4. 打包...'
mvn install
echo '5. 后台运行jar包...'
nohup java -jar target/manage-0.0.1-SNAPSHOT.jar > target/manage.log &