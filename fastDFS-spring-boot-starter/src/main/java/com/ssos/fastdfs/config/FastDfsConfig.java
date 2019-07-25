package com.ssos.fastdfs.config;

import com.luhuiguo.fastdfs.FdfsProperties;
import com.luhuiguo.fastdfs.conn.ConnectionManager;
import com.luhuiguo.fastdfs.conn.FdfsConnectionPool;
import com.luhuiguo.fastdfs.conn.TrackerConnectionManager;
import com.luhuiguo.fastdfs.service.DefaultGenerateStorageClient;
import com.luhuiguo.fastdfs.service.DefaultTrackerClient;
import com.luhuiguo.fastdfs.service.GenerateStorageClient;
import com.luhuiguo.fastdfs.service.TrackerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

/**
 * @ClassName: FastDfsConfig
 * @Description: dto
 * @Author: xwl
 * @Date: 2019-07-16 16:09
 * @Vsersion: 1.0
 */
@Configuration
public class FastDfsConfig {
    @Autowired
    FdfsProperties fdfsProperties;

    @Bean
    @Lazy
    public GenerateStorageClient storageClient() {
        FdfsConnectionPool fdfsConnectionPool = new FdfsConnectionPool();
        TrackerClient trackerClient = new DefaultTrackerClient(
                new TrackerConnectionManager(fdfsConnectionPool, fdfsProperties.getTrackerList()));
        ConnectionManager connectionManager = new ConnectionManager(fdfsConnectionPool);
        return new DefaultGenerateStorageClient(trackerClient, connectionManager);
    }

}
