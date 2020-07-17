package com.jiangjibo.elasticsearch.learning.index.create;

import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import com.jiangjibo.elasticsearch.learning.TestApplication;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsRequest;
import org.elasticsearch.action.admin.indices.settings.get.GetSettingsResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.client.indices.GetMappingsResponse;
import org.elasticsearch.cluster.metadata.MappingMetaData;
import org.elasticsearch.common.settings.Settings;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wb-jjb318191
 * @create 2020-07-17 15:22
 */
@Component
public class CreateIndexRequestUsage extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void createIndex() throws IOException {
        GetSettingsRequest settingsRequest = new GetSettingsRequest().indices("bank");
        GetSettingsResponse settingsResponse = restHighLevelClient.indices().getSettings(settingsRequest,
            RequestOptions.DEFAULT);
        Settings settings = settingsResponse.getIndexToSettings().get("bank");

        GetMappingsRequest mappingsRequest = new GetMappingsRequest();
        mappingsRequest.indices("bank").local(true);
        GetMappingsResponse mappingsResponse = restHighLevelClient.indices().getMapping(mappingsRequest,
            RequestOptions.DEFAULT);
        MappingMetaData mappingMetaData = mappingsResponse.mappings().get("bank");

        Map<String, Object> mappings = mappingMetaData.sourceAsMap();

        Map<String, Object> firstnameMappings = (Map<String, Object>)((Map<String, Object>)mappings.get("properties"))
            .get("firstname");
        firstnameMappings.put("type", "keyword");
        firstnameMappings.remove("fields");

        CreateIndexRequest request = new CreateIndexRequest("new_bank");
        request.mapping(mappings);
        //request.settings(settings);

        CreateIndexResponse response = restHighLevelClient.indices().create(request, RequestOptions.DEFAULT);

        System.out.println(response.index());

    }

}
