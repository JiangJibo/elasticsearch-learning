package com.jiangjibo.elasticsearch.learning.aggr;

import java.io.IOException;

import com.jiangjibo.elasticsearch.learning.TestApplication;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.core.CountRequest;
import org.elasticsearch.client.core.CountResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wb-jjb318191
 * @create 2020-07-17 15:08
 */
@Component
public class CountRequestUsage extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testCount() throws IOException {
        CountRequest countRequest = new CountRequest("new_bank");
        CountResponse countResponse = restHighLevelClient.count(countRequest, RequestOptions.DEFAULT);
        System.out.println(countResponse.getCount());
    }
}
