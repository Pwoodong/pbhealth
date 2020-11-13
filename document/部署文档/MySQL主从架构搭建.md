# MySQL主从架构搭建

## 环境准备

|     IP     | 系统     | 数据库版本 | 角色   |
| :--------: | -------- | ---------- | ------ |
| 10.1.1.100 | Centos 8 | MySQL 5.7  | master |
| 10.1.1.200 | Centos 8 | MySQL 5.7  | slave  |

## 数据库主节点配置

### 1、修改数据库配置文件my.conf

```
server-id=1
log-bin=mysql-bin-master
sync-binlog=1
binlog-do-db=running
expire-logs-days=7
show_compatibility_56=on
binlog-ignore-db=information_schema
binlog-ignore-db=performation_schema
binlog-ignore-db=sys
```

### 2、重启数据库并进入数据库

```
systemctl restart mysql.service

mysql -uroot -p
```

### 3、创建同步数据库

```
create database running;
```

### 4、创建同步用户并授权

```
grant replication slave on *.* to 'slave'@'%' identified by '1qaz2wsx';

grant select on running.* to slave@'%';
```

### 5、给同步账号开启远程连接权限

```
use mysql

update user set host = '%' where user = 'slave';
```



## 数据库从节点配置

### 1、修改数据库配置文件my.conf

```
server_id=2
#增加server_id与主节点配置要不一致
```

### 2、重启数据库并进入数据库

```
systemctl restart mysql.service

mysql -uroot -p
```

### 3、创建与主节点一致的数据库

```
create database running;
```

### 4、开启从节点同步服务

```
stop slave;
change master to master_host='10.1.1.100',master_user='slave',master_password='1qaz2wsx';
start slave;
```



## 测试验证

在主库创建一张表，在从库看看是否一致新增

测试结果如下图:

![](C:\Users\Jy\AppData\Roaming\Typora\typora-user-images\image-20201114005245568.png)

