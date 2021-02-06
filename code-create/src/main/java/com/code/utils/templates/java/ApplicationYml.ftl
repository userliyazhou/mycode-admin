server:
port: 9090
servlet:
context-path: /api

spring:
servlet:
multipart:
enabled: true
max-file-size: 20MB
max-request-size: 20MB
datasource:
url: jdbc:mysql://10.16.153.37:31076/fastdp_demo?characterEncoding=UTF-8&useUnicode=true&useSSL=false&tinyInt1isBit=false
username: root
password: gdyy2020
type: com.alibaba.druid.pool.DruidDataSource
driver-class-name: com.mysql.jdbc.Driver
druid:
initial-size: 5 #初始化时建立物理连接的个数
min-idle: 5 #最小连接池数量
max-active: 20 #最大连接池数量 maxIdle已经不再使用
max-wait: 60000 #获取连接时最大等待时间，单位毫秒
test-while-idle: true #申请连接的时候检测，如果空闲时间大于timeBetweenEvictionRunsMillis，执行validationQuery检测连接是否有效。
time-between-eviction-runs-millis: 60000 #既作为检测的间隔时间又作为testWhileIdel执行的依据
min-evictable-idle-time-millis: 30000 #销毁线程时检测当前连接的最后活动时间和当前时间差大于该值时，关闭当前连接
validation-query: select 'x' #用来检测连接是否有效的sql 必须是一个查询语句
test-on-borrow: false #申请连接时会执行validationQuery检测连接是否有效,开启会降低性能,默认为true
test-on-return: false #归还连接时会执行validationQuery检测连接是否有效,开启会降低性能,默认为true
pool-prepared-statements: true #是否缓存preparedStatement,mysql5.5+建议开启
max-pool-prepared-statement-per-connection-size: 20 #当值大于0时poolPreparedStatements会自动修改为true
filters: stat,wall #配置扩展插件
connection-properties: druid.stat.mergeSql=true;druid.stat.slowSqlMillis=500 #通过connectProperties属性来打开mergeSql功能；慢SQL记录
use-global-data-source-stat: true #合并多个DruidDataSource的监控数据
#redis 配置
redis:
database: 0
host: 10.16.153.37
lettuce:
pool:
max-active: 8   #最大连接数据库连接数,设 0 为没有限制
max-idle: 8     #最大等待连接中的数量,设 0 为没有限制
max-wait: -1ms  #最大建立连接等待时间。如果超过此时间将接到异常。设为-1表示无限制。
min-idle: 0     #最小等待连接中的数量,设 0 为没有限制
shutdown-timeout: 100ms
password: gdyy2020
port: 30786
#mybatis plus 设置
mybatis-plus:
mapper-locations: classpath*:xml/*Mapper.xml
global-config:
# 关闭MP3.0自带的banner
banner: false
db-config:
#主键类型  0:"数据库ID自增",1:"该类型为未设置主键类型", 2:"用户输入ID",3:"全局唯一ID (数字类型唯一ID)", 4:"全局唯一ID UUID",5:"字符串全局唯一ID (idWorker 的字符串表示)";
id-type: id_worker_str
# 默认数据库表下划线命名
table-underline: true
logic-delete-value: 1
logic-not-delete-value: 0
configuration:
# 返回类型为Map,显示null对应的字段
call-setters-on-nulls: true

#rocketmq配置
rocketmq:
name-server: 127.0.0.1:9876 #10.74.150.105:9876
producer:
group: yueyun-demo

swagger:
production: false
basic:
enable: false
username: payuser
password: UtRsNpdxTx4Joj4opay

feign.hystrix.enabled: true
#feign调用默认是1000毫秒=1秒   应该设置成更长时间1000 * 60 * 5  = 5分钟  add by six-vision
hystrix.command.default.execution.isolation.thread.timeoutInMilliseconds: 300000
hystrix.command.default.circuitBreaker.sleepWindowInMilliseconds: 300000
hystrix.command.default.circuitBreaker.forceClosed: true
hystrix.command.default.execution.isolation.strategy: SEMAPHORE
#hystrix.command.default.execution.timeout.enabled=false
#请求处理的超时时间  add by six-vision
ribbon.ReadTimeout: 300000
ribbon.SocketTimeout: 300000
#请求连接的超时时间 add by six-vision
ribbon.ConnectTimeout: 30000

#feign接口网关配置
#yueyun:
#  gateway:
#    #url: https://open-api-test.gdiiyy.com
#    appCode: YUEYUN_COMMON
#    appKey: YUEYUN_COMMON_!@#