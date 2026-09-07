import{a as i,c as n,b as a,o as l}from"./app-XcHn6pIB.js";const e={};function p(d,s){return l(),n("div",null,[...s[0]||(s[0]=[a(`<h2 id="dockerfile-创建镜像" tabindex="-1"><a class="header-anchor" href="#dockerfile-创建镜像"><span>dockerfile 创建镜像</span></a></h2><p>Dockerfile是一个文本格式的配置文件，可以使用Dockerfile来快速创建自定义的镜像。</p><p>Dockerfile由一行行命令语句组成，并且支持以#开头的注释行。 一般而言，Dockerfile主体内容分为四部分：</p><ul><li>基础镜像信息</li><li>维护者信息</li><li>镜像操作指令</li><li>容器启动时执行指令</li></ul><p>主体部分首先使用FROM指令指明所基于的镜像名称，接下来一般是使用LABEL指令说明维护者信息。后面则是镜像操作指令，例如RUN指令将对镜像执行跟随的命令。每运行一条RUN指令，镜像添加新的一层，并提交。最后是CMD指令，来指定运行容器时的操作命令。</p><div class="language-dockerfile line-numbers-mode" data-highlighter="shiki" data-ext="dockerfile" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-dockerfile"><span class="line"></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># Dockerfile 集成 ./gradlew clean build 自动构建 JAR，就需要用到 多阶段构建（multi-stage build）。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># 这样你可以不用在本地 ./gradlew clean build  先手动构建 jar，让 Docker 构建流程从拉代码到打包、运行“一步到位”。 适合 CI/CD 流水线或无需提前手动构建的场景。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># -------- 第一阶段：用 JDK 构建 jar --------</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">FROM</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> eclipse-temurin:17-jdk-jammy </span><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">AS</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> builder</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">WORKDIR</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> /build</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># 优化缓存层，先拷贝基础构建文件</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">COPY</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> gradlew .</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">COPY</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> gradle gradle</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">COPY</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> build.gradle .</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">COPY</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> settings.gradle .</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># 拷贝源码</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">COPY</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> src src</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># 构建 jar 包，x test 可选跳过单元测试，加快速度</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">#  Dockerfile 集成 ./gradlew clean build 自动打包 Spring Boot 项目; 适合 CI/CD，代码更新后即可自动打jar并部署。</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">#15 [builder 8/8] RUN ./gradlew clean build -x test</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">#15 0.331 /bin/sh: 1: ./gradlew: Permission denied</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">#15 ERROR: process &quot;/bin/sh -c ./gradlew clean build -x test&quot; did not complete</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># 添加这行，确保 gradlew 可执行</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">RUN</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> chmod +x gradlew </span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">RUN</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> ./gradlew clean build -x test</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># -------- 第二阶段：用 JRE 极简运行 --------</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">FROM</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> eclipse-temurin:17-jre-jammy</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">WORKDIR</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> /app</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># 只拷贝最终的 jar 包进来</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">COPY</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> --from=builder /build/build/libs/*.jar app.jar</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">EXPOSE</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 8081</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># JVM 连接 PostgreSQL：只要 build.gradle 已引入 postgresql 驱动，无需单独写</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># 启动命令如需外部参数可参考参数化写法</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">ENTRYPOINT</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> [</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;java&quot;</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">, </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;-jar&quot;</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">, </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;app.jar&quot;</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">]</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h3 id="运行-dockerfile-构建镜像-idea" tabindex="-1"><a class="header-anchor" href="#运行-dockerfile-构建镜像-idea"><span>运行 Dockerfile 构建镜像（IDEA ）</span></a></h3><h4 id="方法-a-右键-dockerfile" tabindex="-1"><a class="header-anchor" href="#方法-a-右键-dockerfile"><span><strong>方法 A：右键 Dockerfile</strong></span></a></h4><ol><li>右键 <code>Dockerfile</code> → <code>Run &#39;Dockerfile&#39;</code> 或 <code>Create Docker Configuration</code></li><li>弹出配置框： <ul><li><strong>Image tag</strong>：填 <code>my-app:latest</code></li><li><strong>Context folder</strong>：选项目根目录</li></ul></li><li>点击 Run ▶️</li></ol><h4 id="方法-b-用-docker-compose-构建并运行" tabindex="-1"><a class="header-anchor" href="#方法-b-用-docker-compose-构建并运行"><span><strong>方法 B：用 docker-compose 构建并运行</strong></span></a></h4><ol><li>右键 <code>docker-compose.yml</code> → <code>Run &#39;docker-compose.yml&#39;</code></li><li>IDEA 会自动执行 <code>docker compose up</code></li><li>在 Docker 工具窗口里能看到容器在跑</li></ol><div class="language-dockerfile line-numbers-mode" data-highlighter="shiki" data-ext="dockerfile" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-dockerfile"><span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">version: </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&#39;3.8&#39;</span></span>
<span class="line"></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">services:</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">  backend:</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # 如果 Spring Boot、vuecli等 镜像已用 bootBuildImage、dockerfile等 生成，可以省略 build，image 名即上一步build的名称。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # 不写 build: 字段，否则本地会重新 build 镜像。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # docker-compose up 会自动判断本地无此镜像时，从远程仓库下载。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # 镜像名必须和 build/push 时一致。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    #</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # 只要 build 字段存在，compose 会优先用本地源码和Dockerfile重新构建出镜像，而不是去远程仓库拉镜像。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # 一般线上/生产，只写 image 字段，保证镜像一致性和拉取提升速度。开发测试、需要自定义镜像时才用 build 字段。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    #</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # 最佳实践</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # - 使用远程稳定镜像：对于 ElasticSearch、Redis、MySQL 等基础服务，直接用 image: 指定官方镜像</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # - 开发中的应用：如果频繁修改代码，使用 build: 方便开发调试</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # - CI/CD 管道中：构建一次镜像，推送到私有仓库，然后在生产环境只使用 image: 拉取</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    build: # 表示要用 Dockerfile 自动构建镜像。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 就是让 Docker 从 vuecli-app (前端项目根目录) 目录下查找 Dockerfile 并把这个目录及内容作为构建所需文件传给 Docker 引擎。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 这样就可以 COPY . . 把代码复制进镜像里。</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      context: ../travelplanner</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    image: ccr.ccs.tencentyun.com/travelplanner/springboot-app-travelplanner-image:latest</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    user: root  # 临时使用 root 用户运行（仅开发环境，生产不推荐）</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    depends_on:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - db</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    ports:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;8081:8081&quot;</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # Spring Boot 的配置优先级大致如下（高→低）,高优先级覆盖低优先级：</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # - 命令行参数</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # - 环境变量</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # - application.yml 或 application.properties</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # - 代码中的默认值等</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    #</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # 所以你在 docker-compose.yml 写的</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # environment:</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    #   SPRING_DATASOURCE_URL: jdbc:mysql://db:3306/testdb</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # 等价于设置了环境变量，只会覆盖这几个指定的项，不会影响 application.yml 里其它没有覆盖的配置。</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    environment:</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 数据库配置</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      SPRING_DATASOURCE_URL: jdbc:postgresql://db:5432/postgres</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      SPRING_DATASOURCE_USERNAME: postgres</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      SPRING_DATASOURCE_PASSWORD: secret</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # java.nio.file.AccessDeniedException: /workspace/uploads, 这表示 容器内的 /workspace/uploads 目录没有写权限或者当前用户没有权限访问该目录。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 你的配置，uploads 实际就是 /workspace/uploads。只需保证这个目录存在且有写权限，并用 volumes 把主机持久化目录映射到这里，问题即可解决。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 使用 bootBuildImage 或 Maven/Gradle 的 spring-boot:build-image 任务时，生成的镜像默认使用非 root 用户 (通常是 UID 1000) 运行应用，导致无法写入 /workspace/uploads 目录。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #  - 方法一: user: root  # 临时使用 root 用户运行（仅开发环境，生产不推荐）</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #  - 方法二: 修改 Dockerfile，确保目录存在并有正确权限</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #  - 方法三: 使用 volumes 挂载本地目录（并授权）</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      FILE_UPLOAD_DIR: uploads</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # Redis 配置</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      SPRING_REDIS_HOST: redis</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      SPRING_REDIS_PORT: 6379</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # Kafka 配置</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      SPRING_KAFKA_BOOTSTRAP_SERVERS: kafka:9092</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 应用配置</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      SERVER_PORT: 8081</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    networks:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - app-travelplanner-network</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">  db:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    image: ghcr.io/baosystems/postgis:latest</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    restart: always</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    environment:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      POSTGRES_DB: postgres</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      POSTGRES_PASSWORD: secret</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      TZ: UTC</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    volumes:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - travelplanner-pg-local:/var/lib/postgresql/data</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    ports:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;5432:5432&quot;</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    networks:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - app-travelplanner-network</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">  redis:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    image: redis:7.2</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    restart: always</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    container_name: redis</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    ports:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;6379:6379&quot;</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    volumes:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - redis-data:/data</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    networks:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - app-travelplanner-network</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">  zookeeper:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    image: confluentinc/cp-zookeeper:7.3.0</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    restart: always</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    container_name: zookeeper</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    environment:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      ZOOKEEPER_CLIENT_PORT: 2181</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      ZOOKEEPER_TICK_TIME: 2000</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    ports:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;2181:2181&quot;</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    networks:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - app-travelplanner-network</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">  # 检查 Kafka 日志确认启动正常：docker-compose logs kafka</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">  # kafka - docker container ( docker-compose.yml ) + 宿主机 ( application.yml )</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">  kafka:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    image: confluentinc/cp-kafka:7.3.0</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    restart: always</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    container_name: kafka</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # 虽然我们在容器内监听了两个不同的端口（9092和9093），但通过端口映射，外部依然是通过本地的9092端口访问Kafka。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    #       - &quot;9092:9092&quot; - 可选</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    #      - &quot;9093:9093&quot; - 必选</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    ports:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;9092:9092&quot;</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;9093:9093&quot;</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    environment:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      KAFKA_BROKER_ID: 1</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      KAFKA_ZOOKEEPER_CONNECT: zookeeper:2181</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # KAFKA_LISTENERS: 指定 Kafka broker 监听的地址和端口</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 两个监听器（PLAINTEXT 和 PLAINTEXT_HOST）, 我们需要为不同的监听器指定不同的端口</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # PLAINTEXT - docker 容器网络内部通信（比如 Spring Boot 容器使用 服务名访问: kafka:9092 访问）。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # PLAINTEXT_HOST - 主机客户端（IDE 或 kafka tool）访问，用 localhost:29092。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # | 场景                | KAFKA_LISTENERS                        | KAFKA_ADVERTISED_LISTENERS             | ports                   |</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #|---------------------|----------------------------------------|-----------------------------------------|-------------------------|</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #| 仅容器内部通信      | PLAINTEXT://0.0.0.0:9092               | PLAINTEXT://kafka:9092, 告诉容器网络内的客户端，使用 kafka:9092 连接                 | 9092:9092               |</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #| 宿主机/本地也要连接 | PLAINTEXT://0.0.0.0:9092,PLAINTEXT_HOST://0.0.0.0:29092 | PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:29092, 告诉宿主机上的客户端，使用 localhost:29092 连接 | 9092:9092, 29092:29092  |</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 方案一：只用一个监听器（最简单且常用）</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 删除 PLAINTEXT_HOST，只用 PLAINTEXT 即可（容器内部和外部都走 PLAINTEXT://0.0.0.0:9092）。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 方案二：两个监听器但端口要不同（比如容器内监听 9092，容器外监听 9093）</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      #</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 推荐你优先用方案一，删除多余监听器, 然后重启 Kafka 容器即可。</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      KAFKA_LISTENERS: PLAINTEXT://0.0.0.0:9092,PLAINTEXT_HOST://0.0.0.0:9093</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # KAFKA_ADVERTISED_LISTENERS: 告诉客户端如何连接到 Kafka（内部用服务名，外部用localhost）</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092,PLAINTEXT_HOST://localhost:9093</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # # 定义安全协议</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      KAFKA_LISTENER_SECURITY_PROTOCOL_MAP: PLAINTEXT:PLAINTEXT,PLAINTEXT_HOST:PLAINTEXT</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">      # 集群内部通信使用的监听器</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      KAFKA_INTER_BROKER_LISTENER_NAME: PLAINTEXT</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR: 1</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    depends_on:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - zookeeper</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    networks:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - app-travelplanner-network</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">  # Kafka GUI管理工具(可选)</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">  kafka-ui:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    image: provectuslabs/kafka-ui:latest</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    container_name: kafka-ui</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    restart: always</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    ports:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;8060:8080&quot;</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    environment:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      KAFKA_CLUSTERS_0_NAME: local</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      KAFKA_CLUSTERS_0_BOOTSTRAPSERVERS: kafka:9092</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      KAFKA_CLUSTERS_0_ZOOKEEPER: zookeeper:2181</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    depends_on:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - kafka</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    networks:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - app-travelplanner-network</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">  # http://localhost:8848/nacos</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">  nacos:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    image: nacos/nacos-server:v2.3.2</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    container_name: nacos</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    environment:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - MODE=standalone</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - NACOS_AUTH_ENABLE=true    # 打开鉴权（生产建议打开）</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - NACOS_AUTH_TOKEN=nQPZ70cV4TGH5k3l3AFn4DDW4Vh82CyyDrmc3Kn5inE=</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - NACOS_CORE_AUTH_SERVER_IDENTITY_KEY=nacos</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - NACOS_CORE_AUTH_SERVER_IDENTITY_VALUE=nacos</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    ports:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;8848:8848&quot;</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;9848:9848&quot;</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    volumes:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - ./nacos-data/logs:/home/nacos/logs</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - ./nacos-data/data:/home/nacos/data</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    restart: always</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    networks:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - app-travelplanner-network</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">  # http://localhost:8858</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">  sentinel:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    image: bladex/sentinel-dashboard:1.8.6</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    container_name: sentinel-dashboard</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    ports:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;8858:8858&quot;</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    environment:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - JAVA_OPTS=-Dserver.port=8080 -Dcsp.sentinel.dashboard.server=localhost:8080</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">    # 挂载数据目录，保证日志不会因容器重启丢失（日志目录为 /root/logs）。</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    volumes:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">      - ./sentinel-data/logs:/opt/sentinel/logs</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    restart: always</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># 如果你不显式定义网络，Docker Compose 会自动为所有服务创建一个默认的桥接网络（bridge）。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># 自定义网络：通过 networks 配置，你可以创建一个自定义的桥接网络，并将服务连接到该网络</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">#</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># 每个服务都连接到 app-network 自定义网络。</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># 容器可以通过服务名（如 db）互相通信，而不需要使用 IP 地址</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">#</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># docker network ls</span><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">  // 查看 Docker 网络</span></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># docker network inspect app-network</span><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">  // 查看 app-network 的所有容器</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">networks:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">  app-travelplanner-network:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">    driver: bridge</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"># permanent storage of data.</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">volumes:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">  travelplanner-pg-local:</span></span>
<span class="line"><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">  redis-data:</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><h2 id="dockerfile-idea-创建镜像-就是类似-github-action-docker-compose-yml" tabindex="-1"><a class="header-anchor" href="#dockerfile-idea-创建镜像-就是类似-github-action-docker-compose-yml"><span>dockerfile idea 创建镜像 就是类似 github action docker-compose.yml</span></a></h2><hr><h2 id="一、dockerfile、github-actions、docker-compose-yml" tabindex="-1"><a class="header-anchor" href="#一、dockerfile、github-actions、docker-compose-yml"><span>一、Dockerfile、GitHub Actions、docker-compose.yml</span></a></h2><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span>┌─────────────────────────────────────────────────────────────────┐</span></span>
<span class="line"><span>│                        你的开发流程                              │</span></span>
<span class="line"><span>└─────────────────────────────────────────────────────────────────┘</span></span>
<span class="line"><span></span></span>
<span class="line"><span>  ① 写代码 + Dockerfile（在 IDEA 里）</span></span>
<span class="line"><span>              │</span></span>
<span class="line"><span>              ▼</span></span>
<span class="line"><span>  ② git push 到 GitHub</span></span>
<span class="line"><span>              │</span></span>
<span class="line"><span>              ▼</span></span>
<span class="line"><span>  ③ GitHub Actions 自动构建镜像 → 推到 Docker Hub</span></span>
<span class="line"><span>              │</span></span>
<span class="line"><span>              ▼</span></span>
<span class="line"><span>  ④ 服务器上用 docker-compose.yml 拉取镜像并运行</span></span>
<span class="line"><span>     （或者本地 IDEA 里直接用 docker-compose 调试）</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><hr><h2 id="二、idea-项目结构" tabindex="-1"><a class="header-anchor" href="#二、idea-项目结构"><span>二、IDEA 项目结构</span></a></h2><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span>my-project/</span></span>
<span class="line"><span>├── src/                          # 你的源代码</span></span>
<span class="line"><span>├── Dockerfile                    # 定义镜像怎么构建</span></span>
<span class="line"><span>├── docker-compose.yml            # 本地运行/调试用</span></span>
<span class="line"><span>└── .github/</span></span>
<span class="line"><span>    └── workflows/</span></span>
<span class="line"><span>        └── docker-build.yml      # GitHub Actions 自动构建</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><hr><h2 id="三、idea-创建文件" tabindex="-1"><a class="header-anchor" href="#三、idea-创建文件"><span>三、IDEA 创建文件</span></a></h2><h3 id="_1-dockerfile-构建镜像" tabindex="-1"><a class="header-anchor" href="#_1-dockerfile-构建镜像"><span>1. Dockerfile（构建镜像）</span></a></h3><p>右键项目根目录 → <code>New → File → Dockerfile</code></p><p>示例（Node.js 项目）：</p><div class="language-dockerfile line-numbers-mode" data-highlighter="shiki" data-ext="dockerfile" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-dockerfile"><span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">FROM</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> node:20-alpine</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">WORKDIR</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> /app</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">COPY</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> package*.json ./</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">RUN</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> npm install --production</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">COPY</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> . .</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">EXPOSE</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> 3000</span></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">CMD</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;"> [</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;node&quot;</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">, </span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">&quot;server.js&quot;</span><span style="--shiki-light:#393A34;--shiki-dark:#DBD7CAEE;">]</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><details><summary>👉 如果是 Java Spring Boot（点击展开）</summary><pre><code class="language-dockerfile">FROM maven:3.9-eclipse-temurin-17 AS builder
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B package -DskipTests
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT [&quot;java&quot;, &quot;-jar&quot;, &quot;app.jar&quot;]
</code></pre></details><details><summary>👉 如果是 Python Flask/Django（点击展开）</summary><pre><code class="language-dockerfile">FROM python:3.11-slim
WORKDIR /app
COPY requirements.txt .
RUN pip install -r requirements.txt
COPY . .
EXPOSE 5000
CMD [&quot;python&quot;, &quot;app.py&quot;]
</code></pre></details><hr><h3 id="_2-docker-compose-yml-本地运行-调试" tabindex="-1"><a class="header-anchor" href="#_2-docker-compose-yml-本地运行-调试"><span>2. docker-compose.yml（本地运行/调试）</span></a></h3><p>右键项目根目录 → <code>New → File → docker-compose.yml</code></p><div class="language-yaml line-numbers-mode" data-highlighter="shiki" data-ext="yaml" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-yaml"><span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">version</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B5695977;--shiki-dark:#C98A7D77;"> &quot;</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">3.9</span><span style="--shiki-light:#B5695977;--shiki-dark:#C98A7D77;">&quot;</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">services</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">  app</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    build</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">      context</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#2F798A;--shiki-dark:#4C9A91;"> .</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">      dockerfile</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> Dockerfile</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    container_name</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> my-app</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    ports</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#B5695977;--shiki-dark:#C98A7D77;"> &quot;</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">3000:3000</span><span style="--shiki-light:#B5695977;--shiki-dark:#C98A7D77;">&quot;</span><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">       # 改成你项目的端口</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    environment</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> NODE_ENV=development</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    volumes</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> .:/app</span><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">            # 本地代码映射，方便调试</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> /app/node_modules</span><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;"> # 排除 node_modules</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#A0ADA0;--shiki-dark:#758575DD;">  # 如果需要数据库，可以加：</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">  db</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    image</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> postgres:16</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    container_name</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> my-db</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    environment</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> POSTGRES_USER=myuser</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> POSTGRES_PASSWORD=mypass</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> POSTGRES_DB=mydb</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    ports</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#B5695977;--shiki-dark:#C98A7D77;"> &quot;</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">5432:5432</span><span style="--shiki-light:#B5695977;--shiki-dark:#C98A7D77;">&quot;</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    volumes</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> db-data:/var/lib/postgresql/data</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">volumes</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">  db-data</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><hr><h3 id="_3-github-actions-自动构建" tabindex="-1"><a class="header-anchor" href="#_3-github-actions-自动构建"><span>3. GitHub Actions（自动构建）</span></a></h3><p>在 IDEA 里：</p><ol><li>右键项目根目录 → <code>New → Directory → .github</code></li><li>在 <code>.github</code> 里再建 <code>workflows</code> 目录</li><li>在 <code>workflows</code> 里新建文件 <code>docker-build.yml</code></li></ol><div class="language-yaml line-numbers-mode" data-highlighter="shiki" data-ext="yaml" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-yaml"><span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">name</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> Build and Push Docker Image</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;">on</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">  push</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    branches</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> main</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> develop</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">jobs</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">  build-and-push</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    runs-on</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> ubuntu-latest</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">    steps</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#998418;--shiki-dark:#B8A965;"> name</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> Checkout source</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">        uses</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> actions/checkout@v4</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#998418;--shiki-dark:#B8A965;"> name</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> Set up Docker Buildx</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">        uses</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> docker/setup-buildx-action@v3</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#998418;--shiki-dark:#B8A965;"> name</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> Log in to Docker Hub</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">        uses</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> docker/login-action@v3</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">        with</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">          username</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> \${{ secrets.DOCKERHUB_USERNAME }}</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">          password</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> \${{ secrets.DOCKERHUB_TOKEN }}</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#998418;--shiki-dark:#B8A965;"> name</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> Set image tag</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">        id</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> vars</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">        run</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;"> |</span></span>
<span class="line"><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">          IMAGE=&quot;\${{ secrets.DOCKERHUB_USERNAME }}/my-app&quot;</span></span>
<span class="line"><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">          BRANCH=&quot;\${GITHUB_REF_NAME}&quot;</span></span>
<span class="line"><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">          </span></span>
<span class="line"><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">          if [[ &quot;$BRANCH&quot; == &quot;main&quot; ]]; then</span></span>
<span class="line"><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">            TAG=&quot;latest&quot;</span></span>
<span class="line"><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">          else</span></span>
<span class="line"><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">            TAG=&quot;dev&quot;</span></span>
<span class="line"><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">          fi</span></span>
<span class="line"><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">          </span></span>
<span class="line"><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;">          echo &quot;IMAGE=\${IMAGE}:\${TAG}&quot; &gt;&gt; $GITHUB_OUTPUT</span></span>
<span class="line"></span>
<span class="line"><span style="--shiki-light:#999999;--shiki-dark:#666666;">      -</span><span style="--shiki-light:#998418;--shiki-dark:#B8A965;"> name</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> Build and push</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">        uses</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> docker/build-push-action@v6</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">        with</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">          context</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#2F798A;--shiki-dark:#4C9A91;"> .</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">          file</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> ./Dockerfile</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">          push</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#1E754F;--shiki-dark:#4D9375;"> true</span></span>
<span class="line"><span style="--shiki-light:#998418;--shiki-dark:#B8A965;">          tags</span><span style="--shiki-light:#999999;--shiki-dark:#666666;">:</span><span style="--shiki-light:#B56959;--shiki-dark:#C98A7D;"> \${{ steps.vars.outputs.IMAGE }}</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><hr><h2 id="四、idea-图形化" tabindex="-1"><a class="header-anchor" href="#四、idea-图形化"><span>四、IDEA （图形化）</span></a></h2><h3 id="step-1-确保-docker-插件已启用" tabindex="-1"><a class="header-anchor" href="#step-1-确保-docker-插件已启用"><span>Step 1：确保 Docker 插件已启用</span></a></h3><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span>Settings → Plugins → 搜索 &quot;Docker&quot; → 确认已安装并启用</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div></div></div><h3 id="step-2-连接本地-docker" tabindex="-1"><a class="header-anchor" href="#step-2-连接本地-docker"><span>Step 2：连接本地 Docker</span></a></h3><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span>Settings → Build, Execution, Deployment → Docker → 点击 + → 选择 Docker for Mac/Windows/Unix Socket</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div></div></div><p>连接成功后，IDEA 右侧会出现 <strong>Docker 工具窗口</strong>。</p><h3 id="step-3-运行-dockerfile-构建镜像-idea" tabindex="-1"><a class="header-anchor" href="#step-3-运行-dockerfile-构建镜像-idea"><span>Step 3：运行 Dockerfile 构建镜像（IDEA ）</span></a></h3><p><strong>方法 A：右键 Dockerfile</strong></p><ol><li>右键 <code>Dockerfile</code> → <code>Run &#39;Dockerfile&#39;</code> 或 <code>Create Docker Configuration</code></li><li>弹出配置框： <ul><li><strong>Image tag</strong>：填 <code>my-app:latest</code></li><li><strong>Context folder</strong>：选项目根目录</li></ul></li><li>点击 Run ▶️</li></ol><p><strong>方法 B：用 docker-compose 构建并运行</strong></p><ol><li>右键 <code>docker-compose.yml</code> → <code>Run &#39;docker-compose.yml&#39;</code></li><li>IDEA 会自动执行 <code>docker compose up</code></li><li>在 Docker 工具窗口里能看到容器在跑</li></ol><h3 id="step-4-推送到-github-触发-actions" tabindex="-1"><a class="header-anchor" href="#step-4-推送到-github-触发-actions"><span>Step 4：推送到 GitHub 触发 Actions</span></a></h3><p>在 IDEA 里：</p><ol><li><code>Git → Commit</code> 提交代码</li><li><code>Git → Push</code> 推送到 GitHub</li><li>打开 GitHub 仓库 → <code>Actions</code> 页面，看到 workflow 自动运行</li><li>构建完成后，镜像自动推到 Docker Hub</li></ol><hr><h2 id="五、完整流程图" tabindex="-1"><a class="header-anchor" href="#五、完整流程图"><span>五、完整流程图</span></a></h2><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span>┌──────────────────────────────────────────────────────────────────────┐</span></span>
<span class="line"><span>│                           IDEA 里的操作                              │</span></span>
<span class="line"><span>├──────────────────────────────────────────────────────────────────────┤</span></span>
<span class="line"><span>│                                                                      │</span></span>
<span class="line"><span>│   1. 写代码 + Dockerfile + docker-compose.yml                        │</span></span>
<span class="line"><span>│              │                                                       │</span></span>
<span class="line"><span>│              ▼                                                       │</span></span>
<span class="line"><span>│   2. 右键 docker-compose.yml → Run                                   │</span></span>
<span class="line"><span>│      （本地调试，看应用能不能跑起来）                                  │</span></span>
<span class="line"><span>│              │                                                       │</span></span>
<span class="line"><span>│              ▼                                                       │</span></span>
<span class="line"><span>│   3. Git → Commit → Push 到 GitHub                                   │</span></span>
<span class="line"><span>│              │                                                       │</span></span>
<span class="line"><span>└──────────────┼───────────────────────────────────────────────────────┘</span></span>
<span class="line"><span>               │</span></span>
<span class="line"><span>               ▼</span></span>
<span class="line"><span>┌──────────────────────────────────────────────────────────────────────┐</span></span>
<span class="line"><span>│                        GitHub Actions 自动                           │</span></span>
<span class="line"><span>├──────────────────────────────────────────────────────────────────────┤</span></span>
<span class="line"><span>│                                                                      │</span></span>
<span class="line"><span>│   4. 检测到 push → 读取 Dockerfile → docker build                    │</span></span>
<span class="line"><span>│              │                                                       │</span></span>
<span class="line"><span>│              ▼                                                       │</span></span>
<span class="line"><span>│   5. docker push → 镜像推到 Docker Hub                               │</span></span>
<span class="line"><span>│                                                                      │</span></span>
<span class="line"><span>└──────────────────────────────────────────────────────────────────────┘</span></span>
<span class="line"><span>               │</span></span>
<span class="line"><span>               ▼</span></span>
<span class="line"><span>┌──────────────────────────────────────────────────────────────────────┐</span></span>
<span class="line"><span>│                      服务器上部署（可选）                             │</span></span>
<span class="line"><span>├──────────────────────────────────────────────────────────────────────┤</span></span>
<span class="line"><span>│                                                                      │</span></span>
<span class="line"><span>│   6. 服务器上的 docker-compose.yml：                                 │</span></span>
<span class="line"><span>│                                                                      │</span></span>
<span class="line"><span>│      services:                                                       │</span></span>
<span class="line"><span>│        app:                                                          │</span></span>
<span class="line"><span>│          image: username/my-app:latest   # 直接用 Hub 上的镜像       │</span></span>
<span class="line"><span>│          ports:                                                      │</span></span>
<span class="line"><span>│            - &quot;3000:3000&quot;                                             │</span></span>
<span class="line"><span>│                                                                      │</span></span>
<span class="line"><span>│   7. 执行: docker compose pull &amp;&amp; docker compose up -d               │</span></span>
<span class="line"><span>│                                                                      │</span></span>
<span class="line"><span>└──────────────────────────────────────────────────────────────────────┘</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div><div class="line-number"></div></div></div><hr><h2 id="六、github-secrets-配置-必须做" tabindex="-1"><a class="header-anchor" href="#六、github-secrets-配置-必须做"><span>六、GitHub Secrets 配置（必须做）</span></a></h2><p>在 GitHub 仓库页面：</p><div class="language- line-numbers-mode" data-highlighter="shiki" data-ext="" style="--shiki-light:#393a34;--shiki-dark:#dbd7caee;--shiki-light-bg:#ffffff;--shiki-dark-bg:#121212;"><pre class="shiki shiki-themes vitesse-light vitesse-dark vp-code"><code class="language-"><span class="line"><span>Settings → Secrets and variables → Actions → New repository secret</span></span></code></pre><div class="line-numbers" aria-hidden="true" style="counter-reset:line-number 0;"><div class="line-number"></div></div></div><p>添加：</p><table><thead><tr><th>Name</th><th>Value</th></tr></thead><tbody><tr><td><code>DOCKERHUB_USERNAME</code></td><td>你的 Docker Hub 用户名</td></tr><tr><td><code>DOCKERHUB_TOKEN</code></td><td>Docker Hub 生成的 Access Token</td></tr></tbody></table><hr><h2 id="七、总结对比" tabindex="-1"><a class="header-anchor" href="#七、总结对比"><span>七、总结对比</span></a></h2><table><thead><tr><th>文件</th><th>作用</th><th>在哪里执行</th></tr></thead><tbody><tr><td><strong>Dockerfile</strong></td><td>定义镜像怎么构建</td><td>本地 IDEA / GitHub Actions</td></tr><tr><td><strong>docker-compose.yml</strong></td><td>定义多个容器怎么一起运行</td><td>本地 IDEA / 服务器</td></tr><tr><td><strong>GitHub Actions</strong></td><td>自动化构建 + 推送镜像</td><td>GitHub 云端</td></tr></tbody></table><hr>`,64)])])}const k=i(e,[["render",p]]),t=JSON.parse('{"path":"/zh/npmDoc/htigal0i/","title":"dockerfile 创建镜像","lang":"zh","frontmatter":{"title":"dockerfile 创建镜像","createTime":"2026/01/31 18:20:14","permalink":"/zh/npmDoc/htigal0i/"},"readingTime":{"minutes":9.91,"words":2974},"git":{"createdTime":1769883976000,"updatedTime":1770144228000,"contributors":[{"name":"QuantumBitstream","username":"QuantumBitstream","email":"2273664Z@student.gla.ac.uk","commits":5,"avatar":"https://avatars.githubusercontent.com/QuantumBitstream?v=4","url":"https://github.com/QuantumBitstream"}]},"filePathRelative":"zh/npmDoc/cofig/docker/dockerfile 创建镜像.md","headers":[]}');export{k as comp,t as data};
