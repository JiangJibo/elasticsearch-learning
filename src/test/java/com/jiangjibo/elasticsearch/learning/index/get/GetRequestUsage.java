package com.jiangjibo.elasticsearch.learning.index.get;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import com.jiangjibo.elasticsearch.learning.TestApplication;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wb-jjb318191
 * @create 2020-07-16 15:01
 */
@Component
public class GetRequestUsage extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void getRequest() throws IOException {
        FetchSourceContext context = new FetchSourceContext(true, new String[]{"address"}, null);

        GetRequest request = new GetRequest("bank").id("1").storedFields("_source", "_id");
        request.fetchSourceContext(context);
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response));
    }
}
