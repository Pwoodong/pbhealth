# Nginx配置

## 环境准备

| IP         | 系统     | 服务                                | 备注                      |
| ---------- | -------- | ----------------------------------- | ------------------------- |
| 10.1.1.110 | Centos 8 | nginx、采集服务、展示服务、前台服务 | ngixn做正反代理和负载均衡 |
| 10.1.1.100 | Centos 8 | 采集服务、展示服务、前台服务        |                           |
| 10.1.1.200 | Centos 8 | 采集服务、展示服务、前台服务        |                           |

官网下载安装nginx

系统安装路径:/usr/local/nginx

## 正反向代理配置

1、进入配置路径

```
/usr/local/nginx/conf
```

2、修改配置文件，在sever中增加如下配置

```
proxy_set_header Host   $host;
proxy_set_header X-Real-IP  $remote_addr;
proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
proxy_connect_timeout 300;
proxy_read_timeout 300;
proxy_send_timeout 300;

location /health {
    proxy_pass   http://health/health-collect;
}

location /health-web {
    proxy_pass   http://health-web/health;	
}
```



## 负载均衡配置

3、在配置文件修改http中增加如下配置

```
upstream health {
   server 10.1.1.100:8880;
   server 10.1.1.200:8880;
}

upstream health-web {
   server 10.1.1.100:9080;
   server 10.1.1.110:9080;
   server 10.1.1.200:9080;
}
```

## 说明

虚拟机10.1.1.100机器的8880端口是采集服务、9080是前台服务；

虚拟机10.1.1.200机器的8880端口是采集服务接口、9080是前台服务；

虚拟机10.1.1.110机器的80端口是代理接口、9080是前台服务；