package com.jiangjibo.elasticsearch.learning.index.index;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import com.jiangjibo.elasticsearch.learning.TestApplication;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.ReindexRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wb-jjb318191
 * @create 2020-07-17 14:46
 */
@Component
public class ReindexRequestUsage extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testReindex() throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.rangeQuery("age").gte(30).lte(50));

        ReindexRequest reindexRequest = new ReindexRequest();
        reindexRequest.getSearchRequest().indices("bank").source(sourceBuilder);
        reindexRequest.getDestination().index("new_bank");


        BulkByScrollResponse response = restHighLevelClient.reindex(reindexRequest, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(response));
    }
}
