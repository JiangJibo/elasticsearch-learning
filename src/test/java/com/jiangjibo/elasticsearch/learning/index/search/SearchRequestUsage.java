package com.jiangjibo.elasticsearch.learning.index.search;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import com.jiangjibo.elasticsearch.learning.TestApplication;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wb-jjb318191
 * @create 2020-07-17 10:49
 */
@Component
public class SearchRequestUsage extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testTermQuery() throws IOException {
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchQuery("firstname", "Roberta"));
        sourceBuilder.query(QueryBuilders.termQuery("firstname.keyword", "Roberta"));
        SearchRequest searchRequest = new SearchRequest("bank").source(sourceBuilder);
        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response));
    }

}
