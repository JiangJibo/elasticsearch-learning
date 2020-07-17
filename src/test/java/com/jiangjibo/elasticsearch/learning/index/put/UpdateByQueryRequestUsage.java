package com.jiangjibo.elasticsearch.learning.index.put;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import com.google.common.collect.ImmutableMap;
import com.jiangjibo.elasticsearch.learning.TestApplication;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.UpdateByQueryRequest;
import org.elasticsearch.script.Script;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.elasticsearch.script.Script.DEFAULT_SCRIPT_LANG;
import static org.elasticsearch.script.Script.DEFAULT_SCRIPT_TYPE;

/**
 * @author wb-jjb318191
 * @create 2020-07-17 10:20
 */
@Component
public class UpdateByQueryRequestUsage extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testUpdateByQuery() throws IOException {
        QueryBuilder queryBuilder = QueryBuilders.termQuery("firstname", "Jiang");

        Script script = new Script(DEFAULT_SCRIPT_TYPE, DEFAULT_SCRIPT_LANG,
            "if(ctx._source.firstname == params.firstname){ ctx.op = 'delete'}  else { ctx.op = 'none' }",
            ImmutableMap.of("firstname", "Jiang"));

        UpdateByQueryRequest request = new UpdateByQueryRequest("bank").setQuery(queryBuilder).setScript(script);

        BulkByScrollResponse response = restHighLevelClient.updateByQuery(request, RequestOptions.DEFAULT);

        System.out.println(JSON.toJSONString(response));

    }

}
