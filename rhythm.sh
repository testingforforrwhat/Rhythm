
cd /usr/local
nohup java -jar Rhythm-1.0-SNAPSHOT.jar > out.log 2>&1 &

lsof -i :8001

# 强制终止程序的方式，可能会导致数据丢失或别的问题。确保您了解关闭程序可能带来的影响，并在必要时备份数据
kill -9 <PID>

lsof -i :8001

tail -f -n 10 out.log


cd /usr/local/redis
ls -ltr
ps -elf | grep redis
cd /usr/local/redis/bin
./redis-server ../conf/redis.conf
ps -elf | grep redis

cd /usr/local/zookeeper/bin
./zkServer.sh status
./zkServer.sh start

cd /usr/local/kafka/bin/

./kafka-server-start.sh -daemon /usr/local/kafka/config/server.properties

./kafka-topics.sh --list --bootstrap-server 127.0.0.1:9092

