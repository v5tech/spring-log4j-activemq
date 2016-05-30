# spring-log4j-activemq

将log4j日志输出到activemq

首先启动product项目中的activemq插件

```
mvn activemq:run
```

接着启动logging项目。

```
mvn tomcat:run
```

最后运行product项目中的Message或Send观察控制台输出。

