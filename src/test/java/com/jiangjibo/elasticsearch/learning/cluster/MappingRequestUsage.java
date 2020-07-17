package com.jiangjibo.elasticsearch.learning.cluster;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import com.jiangjibo.elasticsearch.learning.TestApplication;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.GetMappingsRequest;
import org.elasticsearch.client.indices.GetMappingsResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wb-jjb318191
 * @create 2020-07-17 15:38
 */
@Component
public class MappingRequestUsage extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void getMapping() throws IOException {
        GetMappingsRequest request = new GetMappingsRequest();
        request.indices("bank").local(true);
        GetMappingsResponse response = restHighLevelClient.indices().getMapping(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response.mappings()));
    }
}
