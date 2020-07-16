package com.jiangjibo.elasticsearch.learning.index.index;

import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import com.jiangjibo.elasticsearch.learning.TestApplication;
import com.jiangjibo.elasticsearch.learning.model.BankModel;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author wb-jjb318191
 * @create 2020-07-16 11:28
 */
@Component
public class IndexRequestUsage extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void index() throws IOException {
        IndexRequest indexRequest = new IndexRequest("bank");
        BankModel bankModel = new BankModel();
        bankModel.setAccount_number(1);
        bankModel.setBalance(39565);
        bankModel.setFirstname("jiang");
        bankModel.setLastname("jibo");
        bankModel.setAge(30);
        bankModel.setGender("M");
        bankModel.setAddress("中国浙江省杭州市余杭区阿里巴巴");
        bankModel.setEmployer("Pyrami");
        bankModel.setEmail("q450210669@163.com");
        bankModel.setCity("杭州");
        bankModel.setState("IL");
        indexRequest.source(JSON.parseObject(JSON.toJSONString(bankModel), Map.class));
        IndexResponse response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response));
    }

}
