package h5EDULive.web.dto;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * JSON返回
 * @Author ZhangRongrong
 */
public class JsonResult {
    public static JSONObject strToJson(String str) {
        Map suc = new HashMap();
        suc.put("data", str);
        return new JSONObject(suc);
    }

    public static JSONArray listToJson(List<String> list)
    {
        JSONArray res = JSONArray.parseArray(JSON.toJSONString(list));
        return res;
    }
}
