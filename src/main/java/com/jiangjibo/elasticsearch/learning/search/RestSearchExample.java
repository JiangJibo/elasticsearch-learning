package com.jiangjibo.elasticsearch.learning.search;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wb-jjb318191
 * @create 2020-09-02 14:29
 */
@Component
public class RestSearchExample {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    //@PostConstruct
    public void rangeQuery() throws IOException {
        SearchRequest searchRequest = new SearchRequest("ipv4_data");
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(new RangeQueryBuilder("start").gte(100));
        searchRequest.source(searchSourceBuilder);

        System.out.println(searchRequest.source().toString());


        SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        System.out.println(response.getHits().getHits());
    }

}
