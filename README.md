# SpringBoot + MySQL CRUD Demo

一个基于 SpringBoot 2.7 + MySQL + JPA 的用户管理 CRUD 示例项目。

## 项目结构

```
src/main/java/com/example/crud/
├── CrudDemoApplication.java          # 启动类
├── controller/
│   └── UserController.java           # 用户控制器
├── dto/
│   ├── UserCreateDTO.java            # 创建请求体
│   ├── UserUpdateDTO.java            # 更新请求体
│   └── UserQueryDTO.java             # 查询条件
├── entity/
│   └── User.java                     # 用户实体
├── exception/
│   └── GlobalExceptionHandler.java   # 全局异常处理
├── repository/
│   └── UserRepository.java           # 数据访问层
├── service/
│   └── UserService.java              # 业务逻辑层
└── vo/
    ├── Result.java                    # 统一响应
    └── PageResult.java               # 分页结果
```

## 快速开始

### 1. 准备数据库

```bash
mysql -u root -p < sql/init.sql
```

### 2. 修改配置

编辑 `src/main/resources/application.yml`，修改数据库连接信息：

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/crud_demo?useUnicode=true&characterEncoding=utf-8&useSSL=false&serverTimezone=Asia/Shanghai
    username: root
    password: your_password
```

### 3. 启动项目

```bash
mvn spring-boot:run
```

服务默认运行在 `http://localhost:8080`

## API 接口

| 方法   | 路径              | 说明         |
|--------|-------------------|-------------|
| POST   | `/api/users`      | 创建用户     |
| GET    | `/api/users`      | 分页查询用户  |
| GET    | `/api/users/{id}` | 查询单个用户  |
| PUT    | `/api/users/{id}` | 更新用户     |
| DELETE | `/api/users/{id}` | 删除用户     |

### 接口示例

**创建用户**
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"username":"zhangsan","email":"zhangsan@example.com","phone":"13800138000","status":1}'
```

**分页查询**
```bash
curl "http://localhost:8080/api/users?username=zhang&page=1&size=10"
```

**查询详情**
```bash
curl http://localhost:8080/api/users/{id}
```

**更新用户**
```bash
curl -X PUT http://localhost:8080/api/users/{id} \
  -H "Content-Type: application/json" \
  -d '{"email":"new@example.com","phone":"13900139000","status":0}'
```

**删除用户**
```bash
curl -X DELETE http://localhost:8080/api/users/{id}
```

## 技术选型

- SpringBoot 2.7.18
- Spring Data JPA
- MySQL + MySQL Connector
- Lombok
- Spring Validation
