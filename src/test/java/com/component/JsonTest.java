package com.component;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ian.Su
 * @version $Id: JsonTest.java, v 0.1 2017/7/11 16:52 Ian.Su Exp $
 */
public class JsonTest {



    public static void main(String [] agrs){

//        Map<String,Object> a = new HashMap<>();
//        a.put("a","bb");
//        a.put("b",23);
//
//        System.out.println(a.toString());
//
//        String json = JSONObject.toJSONString(a);
//
//        System.out.println(json);
//
//        System.out.println(JSONObject.parse(json).toString());


          System.out.println( JSONObject.toJSON("abc") );




    }


}
