#FROM openjdk:8-jdk-alpine：基础镜像环境 JDK1.8
FROM openjdk:8-jre-alpine
RUN mkdir -p /data/applogs/cat
RUN mkdir -p /data/appdatas/cat
RUN chmod 777 -R /data
COPY ./client.xml /data/appdatas/cat/client.xml
#VOLUME /tmp：指定了挂载目录
VOLUME /tmp
#ARG JAR_FILE：和上面 POM 文件中配置的 buildArgs 对应，动态获取打包后的名称
ARG JAR_FILE
#ADD ${JAR_FILE} app.jar：把生成的 jar 包拷贝到 Docker 容器中并命名为 app.jar
ADD ${JAR_FILE} app.jar
#修改 Tomcat 随机数生成方式，加快 Tomcat 启动
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]