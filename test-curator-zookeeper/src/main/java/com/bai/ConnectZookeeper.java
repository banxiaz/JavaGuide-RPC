package com.bai;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

import java.util.Arrays;
import java.util.List;

// create set/get ls/ stat detele
public class ConnectZookeeper {
    private static final int BASE_SLEEP_TIME = 1000;
    private static final int MAX_RETRIES = 3;

    public static void main(String[] args) throws Exception {
        // Retry strategy. Retry 3 times, and will increase the sleep time between retries.
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(BASE_SLEEP_TIME, MAX_RETRIES);
        CuratorFramework zkClient = CuratorFrameworkFactory.builder()
                // the server to connect to (can be a server list)
                .connectString("127.0.0.1:2181")
                .retryPolicy(retryPolicy)
                .build();
        zkClient.start();

        getChildrenPaths(zkClient, "/");
        getNodeInfo(zkClient, "/");

        createEphemeralNodeAndData(zkClient, "/mynode", "hello data");
        getChildrenPaths(zkClient, "/mynode");
        getNodeData(zkClient,"/mynode");
        Thread.sleep(1000 * 20);


    }

    public static void getChildrenPaths(CuratorFramework zkClient, String path) throws Exception {
        List<String> childrenPaths = zkClient.getChildren().forPath(path);
        for (String s : childrenPaths) {
            System.out.println(s);
        }
    }

    public static void getNodeInfo(CuratorFramework zkClient, String path) throws Exception {
        byte[] info = zkClient.getData().forPath(path); //获取节点的数据内容
        System.out.println(Arrays.toString(info));
    }

    // 创建持久化节点，在父节点不存在时也可以创建
    public static void createPersistentNode(CuratorFramework zkClient, String path) throws Exception {
        zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path);
    }

    // 创建临时节点
    public static void createEphemeralNode(CuratorFramework zkClient, String path) throws Exception {
        zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path);
    }

    // 创建临时节点，并指定数据内容
    public static void createEphemeralNodeAndData(CuratorFramework zkClient, String path, String data) throws Exception {
        zkClient.create().creatingParentContainersIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, data.getBytes());
    }

    // 检测节点是否存在
    public static void checkExists(CuratorFramework zkClient, String path) throws Exception {
        System.out.println(zkClient.checkExists().forPath(path));
    }

    // 删除一个节点
    public static void deleteNode(CuratorFramework zkClient, String path) throws Exception {
        zkClient.delete().forPath(path);
    }

    // 删除一个节点以及其下的所有子节点
    public static void deleteNodes(CuratorFramework zkClient, String path) throws Exception {
        zkClient.delete().deletingChildrenIfNeeded().forPath(path);
    }

    // 获取节点数据内容
    public static void getNodeData(CuratorFramework zkClient, String path) throws Exception {
        byte[] data = zkClient.getData().forPath(path);//获取节点的数据内容
        System.out.println(new String(data));
    }

    // 更新节点内容
    public static void updateData(CuratorFramework zkClient, String path, String data) throws Exception {
        zkClient.setData().forPath(path, data.getBytes());//更新节点数据内容
    }
}
