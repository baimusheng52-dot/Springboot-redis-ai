# fourtext-redis

基于 Maven 的最小 Spring Boot + Redis 项目。

## 按步骤验证

需要先有一个正在运行的 Redis。项目默认连接 `localhost:6379`，可先在终端检查：

```bash
redis-cli ping
```

如果返回 `PONG`，Redis 已就绪。当前本机 Redis 免密；如果你的 Redis 要密码，跳到“密码配置”。

### 1. 启动 Spring Boot

在终端 A（保持运行，不要关闭）执行：

```bash
mvn spring-boot:run
```

看到 `Started FourtextRedisApplication` 后，应用已启动。

### 2. 写入一个值

另开终端 B 执行：

```bash
curl -i -X PUT 'http://localhost:8080/redis/greeting?value=hello-redis'
```

预期状态码为 `200`，响应正文为 `hello-redis`。

### 3. 读取刚才写入的值

仍在终端 B 执行：

```bash
curl -i 'http://localhost:8080/redis/greeting'
```

预期状态码为 `200`，响应正文仍是 `hello-redis`。这说明值已写入 Redis 并成功读回。

也可以检查 Spring Boot 示例接口：

```bash
curl 'http://localhost:8080/hello'
```

预期输出：`Hello, Spring Boot!`

## 密码配置

Redis 默认免密。如果你的 Redis 配置了密码，在启动应用的终端 A 中先设置环境变量，再启动：

```bash
export REDIS_PASSWORD='你的Redis密码'
mvn spring-boot:run
```

密码不会保存在项目文件中。