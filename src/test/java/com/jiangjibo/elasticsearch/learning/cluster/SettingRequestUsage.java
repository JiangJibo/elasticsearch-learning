package com.jiangjibo.elasticsearch.learning.cluster;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import com.jiangjibo.elasticsearch.learning.TestApplication;
import org.elasticsearch.action.admin.cluster.settings.ClusterGetSettingsRequest;
import org.elasticsearch.action.admin.cluster.settings.ClusterGetSettingsResponse;
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsRequest;
import org.elasticsearch.action.admin.cluster.settings.ClusterUpdateSettingsResponse;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wb-jjb318191
 * @create 2020-07-16 13:50
 */
@Component
public class SettingRequestUsage extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void getSettings() throws IOException {
        GetSettingsRequest request = new GetSettingsRequest().indices("bank");
        GetSettingsResponse response = restHighLevelClient.indices().getSettings(request, RequestOptions.DEFAULT);
        String autoCreate = response.getSetting("bank", "action.auto_create_index");
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void updateSettings() throws IOException {
        Settings settings = Settings.builder().put("action.auto_create_index", "false").build();
        ClusterUpdateSettingsRequest request = new ClusterUpdateSettingsRequest();
        request.persistentSettings(settings);
        ClusterUpdateSettingsResponse response = restHighLevelClient.cluster().putSettings(request, RequestOptions.DEFAULT);
        boolean ack = response.isAcknowledged();
    }

    @Test
    public void getClusterSetting() throws IOException {
        ClusterGetSettingsRequest request = new ClusterGetSettingsRequest();
        ClusterGetSettingsResponse response = restHighLevelClient.cluster().getSettings(request,RequestOptions.DEFAULT);
        System.out.println(response.getSetting("action.auto_create_index"));
    }

}
