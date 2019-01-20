package com.gupao.server;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public class RegisterCenterImpl implements IRegisterCenter{
 
    private static CuratorFramework curatorFramework;
 
    static{// 这段代码无非是连接服务器，自己看着写在哪里把
        curatorFramework=CuratorFrameworkFactory.builder().
                connectString(ZkConfig.CONNNECTION_STR). //
                sessionTimeoutMs(4000).
                retryPolicy(new ExponentialBackoffRetry(1000,
                        10)).build();
        curatorFramework.start();
    }
 
    public void register(String serviceName, String serviceAddress) {
        //注册相应的服务
        String servicePath=ZkConfig.ZK_REGISTER_PATH+"/"+serviceName;
 
        try {
            //判断 /registrys/product-service是否存在，不存在则创建
            if(curatorFramework.checkExists().forPath(servicePath)==null){
                curatorFramework.create().creatingParentsIfNeeded().
                        withMode(CreateMode.PERSISTENT).forPath(servicePath,"0".getBytes());
            }
            // 组装节点地址
            String addressPath=servicePath+"/"+serviceAddress;
            String rsNode=curatorFramework.create().withMode(CreateMode.EPHEMERAL).
                    forPath(addressPath,"0".getBytes());
            System.out.println("服务注册成功："+rsNode);
 
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}