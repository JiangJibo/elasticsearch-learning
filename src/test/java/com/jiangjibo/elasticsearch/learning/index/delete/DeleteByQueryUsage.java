package com.jiangjibo.elasticsearch.learning.index.delete;

import java.io.IOException;

import com.jiangjibo.elasticsearch.learning.TestApplication;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.TermQueryBuilder;
import org.elasticsearch.index.reindex.BulkByScrollResponse;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wb-jjb318191
 * @create 2020-07-16 15:48
 */
@Component
public class DeleteByQueryUsage extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void deleteByQuery() throws IOException {
        DeleteByQueryRequest request = new DeleteByQueryRequest("bank");
        request.setConflicts("proceed");
        request.setMaxRetries(3);

        SearchSourceBuilder builder = SearchSourceBuilder.searchSource().query(new TermQueryBuilder("firstname","jiang"));
        request.getSearchRequest().source(builder);


        BulkByScrollResponse response = restHighLevelClient.deleteByQuery(request, RequestOptions.DEFAULT);
    }

}
