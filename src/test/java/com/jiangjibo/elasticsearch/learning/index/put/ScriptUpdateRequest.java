package com.jiangjibo.elasticsearch.learning.index.put;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import com.jiangjibo.elasticsearch.learning.TestApplication;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.script.Script;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wb-jjb318191
 * @create 2020-07-16 17:06
 */
@Component
public class ScriptUpdateRequest extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testUpdateByScript() throws IOException {
        //UpdateRequest request = new UpdateRequest("bank","1").script(new Script("ctx._source.age ++"));
        UpdateRequest request = new UpdateRequest("bank","1").script(new Script("ctx._source.remove('lastname')"));
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response));
    }

}
