package com.jiangjibo.elasticsearch.learning.index.put;

import java.io.IOException;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import com.google.common.collect.ImmutableMap;
import com.jiangjibo.elasticsearch.learning.TestApplication;
import com.jiangjibo.elasticsearch.learning.model.BankModel;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.script.Script;
import org.elasticsearch.script.ScriptType;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.elasticsearch.script.Script.DEFAULT_SCRIPT_LANG;
import static org.elasticsearch.script.Script.DEFAULT_SCRIPT_TYPE;

/**
 * @author wb-jjb318191
 * @create 2020-07-16 17:06
 */
@Component
public class UpdateByScriptRequestUsage extends TestApplication {

    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    public void testUpdateByScript() throws IOException {
        //UpdateRequest request = new UpdateRequest("bank","1").script(new Script("ctx._source.age ++"));
        UpdateRequest request = new UpdateRequest("bank", "1").script(new Script("ctx._source.remove('lastname')"));
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void testScriptIf() throws IOException {
        Script script = new Script(DEFAULT_SCRIPT_TYPE, DEFAULT_SCRIPT_LANG,
            "if(ctx._source.firstname == params.firstname){ ctx.op = 'delete'}  else { ctx.op = 'none' }",
            ImmutableMap.of("firstname", "Amber"));

        UpdateRequest request = new UpdateRequest("bank", "1").script(script).detectNoop(true);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response));
    }

    @Test
    public void testScriptUpsert() throws IOException {
        Script script = new Script(DEFAULT_SCRIPT_TYPE, DEFAULT_SCRIPT_LANG,
            "ctx._source.firstname == params.firstname",
            ImmutableMap.of("firstname", "Jiang"));

        BankModel bankModel = new BankModel();
        bankModel.setAccount_number(1);
        bankModel.setBalance(40000);
        bankModel.setFirstname("Jiang");
        bankModel.setLastname("jibo");
        bankModel.setAge(31);
        bankModel.setGender("M");
        bankModel.setAddress("中国浙江省杭州市余杭区阿里巴巴");
        bankModel.setEmployer("Pyrami");
        bankModel.setEmail("q450210669@163.com");
        bankModel.setCity("杭州");
        bankModel.setState("IL");

        UpdateRequest request = new UpdateRequest("bank", "1003").script(script).detectNoop(true).upsert(
            JSON.parseObject(JSON.toJSONString(bankModel), Map.class));

        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(JSON.toJSONString(response));
    }

}
