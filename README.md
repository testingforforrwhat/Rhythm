
## Rhythm


## **1. 系统概述**

本系统是一个音乐管理和流媒体播放系统，提供以下主要功能：

- 用户管理
- 音乐管理
- 音乐流媒体播放
- 音乐搜索
- 音乐推荐
- 收藏和分享
- 广告管理
- 安全和权限

**开发文档：**  [doc](https://testingforforrwhat.github.io/rhythm_doc/)

**演示地址：**  [demo](http://81.68.159.172/)

**账号密码：** `test / test`

#### 项目源码

|     |   后端源码  |   前端源码  |
|---  |--- | --- |
|  github   |  https://github.com/testingforforrwhat/Rhythm.git   |  https://github.com/testingforforrwhat/rhythm-vue.git   |


## **2. 系统架构设计**

### **2.1 技术选型**

- 后端框架：Spring Boot
- 前端框架：Vue + Element
- 数据库：MySQL
- 安全：Spring Security + JWT
- 文档：Swagger UI
- 构建工具：Maven

|   | 系统组件   | 采用技术            | 官网                                                                                                               |
|---|--------|-----------------|------------------------------------------------------------------------------------------------------------------|
| 1 | 基础框架   | Spring Boot     | https://spring.io/projects/spring-boot                                                                           |
| 2 | ORM 框架 | MyBatis-Plus    | https://baomidou.com                                                                                             |
| 3 | 工具类库   | hutool          | https://hutool.cn                                                                                                |
| 4 | 访问控制   | Spring Security | https://spring.io/projects/spring-security                                                                       |
| 5 | 日志记录   | Logback         | https://logback.qos.ch/                                                                                          |
| 6 | 数据库连接池 | Druid           | https://developer.aliyun.com/article/1395248                                                                     |
| 7 | 前端框架   | Vue3            | https://v2.cn.vuejs.org/v2/api/ <br> https://cn.vuejs.org/api/general.html                                                      |
| 8 | 前端Ui框架 | Element         | https://element.eleme.cn/#/zh-CN/component/quickstart <br> http://element-plus.org/zh-CN/component/overview.html |
| 9 | 前端部署   | Nginx           | https://nginx.org/                                                                                               |

### **2.2. 系统架构**

采用前后端分离架构，前端负责用户界面和交互逻辑，后端负责数据处理和业务逻辑。使用 Spring Boot 框架构建后端 API，提供 RESTful 服务。

- 表示层（Controller）：负责处理用户请求，响应用户。
- 业务逻辑层（Service）：实现业务逻辑处理。
- 数据访问层（Repository）：负责数据持久化操作。






## **2. 数据库设计**

[数据库设计文档](https://testingforforrwhat.github.io/rhythm_blog/2024/05/22/rhythm_Database%20design%20document._1.0.0/)


## **3. [API 设计](http://81.68.159.172/swagger-api/swagger-ui/index.html)**

### **3.1 用户管理**

- - [X] 注册：`POST /api/users/regist`
- - [X] 登录：`POST /api/users/login`
- - [ ] 获取用户信息：`GET /api/users/{user_id}`
- - [ ] 更新用户信息：`PUT /api/users/{user_id}`
- - [ ] 上传头像：`POST /api/users/{user_id}/avatar`

### **3.2 音乐管理**

- - [X] 创建音乐分类：`POST /api/categories`
- - [X] 编辑音乐分类：`Patch /api/categories`
- - [X] 查询每个音乐分类：`GET /api/categories/{category_id}`
- - [X] 查询当前所有音乐分类：`GET /api/categories`
- - [X] 查询满足条件的所有音乐：`POST /api/categories/search`
- - [X] 删除音乐分类：`DELETE /api/categories/{category_id}`
- - [ ] 上传音乐：`POST /api/music`
- - [X] 编辑音乐信息：`PUT /api/music/{music_id}`
- - [X] 删除音乐：`DELETE /api/music/{music_id}`

### **3.3 音乐流媒体播放**

- - [ ] 获取音乐播放地址：`GET /api/music/{music_id}/play`
- - [ ] 随机播放音乐：`GET /api/music/random`
- - [ ] 单曲循环播放音乐：`GET /api/music/{music_id}/loop`
- - [ ] 列表循环播放音乐：`GET /api/music/list/loop`

### **3.4 音乐搜索**

- - [ ] 按关键词搜索音乐：`GET /api/music/search?keyword={keyword}`

### **3.5 音乐推荐**

- - [ ] 获取个性化音乐推荐：`GET /api/music/recommendations`

### **3.6 收藏和分享**

- - [X] 收藏音乐：`token POST /api/favorites`
- - [X] 取消收藏音乐：`token DELETE /api/favorites/{favorite_id}`
- - [X] 获取用户收藏的音乐：`token GET /api/favorites/user/{user_id}`

### **3.7 广告管理**

- - [ ] 查询广告：`GET /api/ads/{ad_id}`
- - [ ] 发布广告：`POST /api/ads`
- - [ ] 编辑广告：`PUT /api/ads/{ad_id}`
- - [ ] 删除广告：`DELETE /api/ads/{ad_id}`

### **3.8 安全和权限**

- - [ ] 用户身份验证：`POST /api/auth/login`
- - [ ] 获取当前登录用户信息：`GET /api/auth/me`

## **4. 相关模块**

```
Rhythm
├─src
│  ├─main
│  │  ├─java
│  │  │  └─com
│  │  │      └─test
│  │  │          ├─bean
│  │  │          │  ├─bo
│  │  │          │  ├─dto
│  │  │          │  ├─po
│  │  │          │  └─vo
│  │  │          ├─config
│  │  │          ├─controller
│  │  │          │  ├─advertise
│  │  │          │  ├─favorites
│  │  │          │  ├─music
│  │  │          │  ├─musicCategories
│  │  │          │  └─users
│  │  │          ├─doc
│  │  │          │  └─sql
│  │  │          ├─exception
│  │  │          ├─handler
│  │  │          ├─interceptor
│  │  │          ├─log
│  │  │          ├─mapper
│  │  │          ├─security
│  │  │          ├─service
│  │  │          │  └─impl
│  │  │          ├─task
│  │  │          └─utils
│  │  └─resources
│  │      ├─com
│  │      │  └─test
│  │      │      └─mapper
│  │      ├─db
│  │      │  └─migration
│  │      ├─mapper
│  │      └─static
│  │          ├─audio
│  │          ├─downloads
│  │          └─uploads
│  └─test
│      └─java
│          └─com
│              └─test
```




## **5. 部署与监控**

- 部署：使用 Docker 容器化部署服务，便于在不同环境下快速部署。
- 监控：使用 [Spring Boot Actuator](http://81.68.159.172/actuator-api/) 监控应用健康状况。

## **6. 其它注意事项**

- 系统应提供友好的用户界面，便于用户操作。
- 系统应支持多用户并发访问。
- 系统应具有良好的可扩展性，能够满足未来业务需求的增长。

## 快速部署

1. clone 项目到本地 `https://github.com/testingforforrwhat/Rhythm.git`
2. 运行数据库脚本，并修改项目中关于数据的配置（ resources 目录 application.properties ）
3. 在 IntelliJ IDEA 中打开 rhythm 项目，启动
4. 运行

**服务端启动，此时地址栏输入 `http://localhost:8001` 即可访问项目。**


## swagger

`http://localhost:8001/swagger-ui/index.html`


---
本开发文档提供了音乐管理系统的基本设计方案，包括系统架构、数据库设计、API设计和安全性考虑。在实际开发过程中，根据需求的变化和技术的进步，设计方案可能会有所调整。
