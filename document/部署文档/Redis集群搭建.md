# Redis集群搭建

## 环境准备

| 虚拟机器   | 端口       | 备注   |
| ---------- | ---------- | ------ |
| 10.1.1.100 | 6379、6380 | 主、从 |
| 10.1.1.110 | 6379、6380 | 主、从 |
| 10.1.1.200 | 6379、6380 | 主、从 |

每个虚拟机下载安装redis，版本号是6.0.3

安装目录:/usr/local/redis-6.0.3

## 安装配置

### 配置文件准备

在虚拟机器10.1.1.100、10.1.1.110、10.1.1.200下

创建集群配置文件目录:/data/redis/cluster

创建主节点配置文件目录:6379

创建从节点配置文件目录:6380

在主、从节点下创建配置文件redis.conf

修改配置文件

```
（1）daemonize yes
（2）port 6379（分别对每个机器的端口号进行设置）
（3）pidfile /var/run/redis_6379.pid # 把pid进程号写入pidfile配置的文件
（4）dir /data/redis/cluster/6379/（指定数据文件存放位置，必须要指定不同的目录位置，不然会丢失数据）
（5）cluster‐enabled yes（启动集群模式）
（6）cluster‐config‐file nodes‐6379.conf（集群节点信息文件，这里800x最好和port对应上）
（7）cluster‐node‐timeout 10000
(8)# bind 127.0.0.1（bind绑定的是自己机器网卡的ip，如果有多块网卡可以配多个ip，代表允许客户端通
过机器的哪些网卡ip去访问，内网一般可以不配置bind，注释掉即可）
(9)protected‐mode no （关闭保护模式）
(10)appendonly yes
如果要设置密码需要增加如下配置：
(11)requirepass 1qaz2wsx (设置redis访问密码)
(12)masterauth 1qaz2wsx (设置集群节点间访问密码，跟上面一致)
```

### 启动

/usr/local/redis-6.0.3/src/redis-server /data/redis/cluster/6379/redis.conf

/usr/local/redis-6.0.3/src/redis-server /data/redis/cluster/6380/redis.conf

#### 查看是否启动

ps -ef|grep redis

#### 集群开启

```
/usr/local/redis-6.0.3/src/redis-cli -a 1qaz2wsx --cluster create --cluster-replicas 1 10.1.1.100:6379 10.1.1.110:6379 10.1.1.200:6379 10.1.1.100:6380 10.1.1.110:6380 10.1.1.200:6380
```

![image-20201122225344360](C:\Users\Jy\AppData\Roaming\Typora\typora-user-images\image-20201122225344360.png)

![image-20201122225437383](C:\Users\Jy\AppData\Roaming\Typora\typora-user-images\image-20201122225437383.png)



#### 验证集群

```
cd /usr/local/redis-6.0.3/src
./redis-cli -a 1qaz2wsx -c -h 10.1.1.100 -p 6379
```



