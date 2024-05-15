
cd /usr/local
nohup java -jar Rhythm-1.0-SNAPSHOT.jar > out.log 2>&1 &

lsof -i :8001

kill -9 <PID>

lsof -i :8001

