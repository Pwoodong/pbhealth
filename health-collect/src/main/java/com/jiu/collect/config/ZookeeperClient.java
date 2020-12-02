package com.jiu.collect.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;

/**
 * Package com.jiu.calculate.config
 * ClassName ZookeeperClient.java
 * Description ZK客户端
 *
 * @author Liaoyj
 * @version V1.0
 * @date 2020-11-30
 **/
public class ZookeeperClient {

    /** ZK客户端 */
    private CuratorFramework curatorFramework = null;

    public CuratorFramework getCuratorFramework() {
        return curatorFramework;
    }

    /** 构造函数 */
    public ZookeeperClient(CuratorFramework curatorFramework){
        this.curatorFramework = curatorFramework;
    }

    /** 创建节点 */
    public void save(String path, String data, CreateMode createMode){
        try{
            curatorFramework.create().creatingParentContainersIfNeeded().withMode(createMode).forPath(path,data.getBytes());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /** 查询节点信息 */
    public String query(String path){
        try{
            byte[] data = curatorFramework.getData().forPath(path);
            if(data != null && data.length > 0){
                return new String(data);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
