import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhanzhan
 * @date 2020/12/7 22:09
 */
@Slf4j
public class JsonTest {
    public static void main(String[] args) {
        //转化成
        JSONObject obj = new JSONObject();
        obj.put("itemUuid", "dafddsfdsfsdf");
        obj.put("top", 0);
        System.out.println("JSONObject:" + obj);

        JSONArray arr = new JSONArray();
        arr.add(obj);
        System.out.println("JSONArray:" + arr);

        String str = JSONArray.toJSONString(arr);
        System.out.println("JSONArray.toJSONString:" + str);
        //解析
        JSONArray jsonArr = JSONArray.parseArray(str);
        System.out.println(jsonArr);
        if (jsonArr.size() > 0) {
            for (int j = 0; j < jsonArr.size(); j++) {
                JSONObject jsonObj = (JSONObject) jsonArr.get(j);
                Map<String, Object> mapO = jsonObj;
                String itemUuid = (String) mapO.get("itemUuid");
                int top = Integer.parseInt(mapO.get("top")
                        .toString());
                System.out.println(itemUuid);
                System.out.println(top);
            }
        }
        //排序
        List<JSONObject> list = JSONArray.parseArray(jsonArr.toJSONString(), JSONObject.class);
        Collections.sort(list, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                int a = o1.getInteger("top");
                int b = o2.getInteger("top");
                if (a < b) {
                    return 1;
                } else if (a == b) {
                    return 0;
                } else
                    return -1;
            }
        });
        JSONArray jsonArray = JSONArray.parseArray(list.toString());
        System.out.println("排序后：" + jsonArray);

        //JSON数组
        //message=[{"headers":{"host":"192.168.133.128","user-agent":"lua-resty-http/0.16.1 (Lua) ngx_lua/9014"},"method":"GET","uri_args":{"productId":"26","shopId":"1"},"raw_reader":"GET /product?productId=26&shopId=1 HTTP/1.1\r\nHost: 192.168.133.128\r\nUser-Agent: lua-resty-http/0.16.1 (Lua) ngx_lua/9014\r\n\r\n","http_version":1.1,"request_module":"product_detail_info"},{"headers":{"host":"192.168.133.128","user-agent":"lua-resty-http/0.16.1 (Lua) ngx_lua/9014"},"method":"GET","uri_args":{"productId":"26","shopId":"1"},"raw_reader":"GET /product?productId=26&shopId=1 HTTP/1.1\r\nHost: 192.168.133.128\r\nUser-Agent: lua-resty-http/0.16.1 (Lua) ngx_lua/9014\r\n\r\n","http_version":1.1,"request_module":"product_detail_info"},{"headers":{"host":"192.168.133.128","user-agent":"lua-resty-http/0.16.1 (Lua) ngx_lua/9014"},"method":"GET","uri_args":{"productId":"26","shopId":"1"},"raw_reader":"GET /product?productId=26&shopId=1 HTTP/1.1\r\nHost: 192.168.133.128\r\nUser-Agent: lua-resty-http/0.16.1 (Lua) ngx_lua/9014\r\n\r\n","http_version":1.1,"request_module":"product_detail_info"}]
        String message = "[{\n" + "\t\"headers\": {\n" + "\t\t\"host\": \"192.168.133.128\",\n"
                + "\t\t\"user-agent\": \"lua-resty-http/0.16.1 (Lua) ngx_lua/9014\"\n" + "\t},\n"
                + "\t\"method\": \"GET\",\n" + "\t\"uri_args\": {\n" + "\t\t\"productId\": \"26\",\n"
                + "\t\t\"shopId\": \"1\"\n" + "\t},\n"
                + "\t\"raw_reader\": \"GET /product?productId=26&shopId=1 HTTP/1.1\\r\\nHost: 192.168.133.128\\r\\nUser-Agent: lua-resty-http/0.16.1 (Lua) ngx_lua/9014\\r\\n\\r\\n\",\n"
                + "\t\"http_version\": 1.1,\n" + "\t\"request_module\": \"product_detail_info\"\n" + "}, {\n"
                + "\t\"headers\": {\n" + "\t\t\"host\": \"192.168.133.128\",\n"
                + "\t\t\"user-agent\": \"lua-resty-http/0.16.1 (Lua) ngx_lua/9014\"\n" + "\t},\n"
                + "\t\"method\": \"GET\",\n" + "\t\"uri_args\": {\n" + "\t\t\"productId\": \"26\",\n"
                + "\t\t\"shopId\": \"1\"\n" + "\t},\n"
                + "\t\"raw_reader\": \"GET /product?productId=26&shopId=1 HTTP/1.1\\r\\nHost: 192.168.133.128\\r\\nUser-Agent: lua-resty-http/0.16.1 (Lua) ngx_lua/9014\\r\\n\\r\\n\",\n"
                + "\t\"http_version\": 1.1,\n" + "\t\"request_module\": \"product_detail_info\"\n" + "}, {\n"
                + "\t\"headers\": {\n" + "\t\t\"host\": \"192.168.133.128\",\n"
                + "\t\t\"user-agent\": \"lua-resty-http/0.16.1 (Lua) ngx_lua/9014\"\n" + "\t},\n"
                + "\t\"method\": \"GET\",\n" + "\t\"uri_args\": {\n" + "\t\t\"productId\": \"26\",\n"
                + "\t\t\"shopId\": \"1\"\n" + "\t},\n"
                + "\t\"raw_reader\": \"GET /product?productId=26&shopId=1 HTTP/1.1\\r\\nHost: 192.168.133.128\\r\\nUser-Agent: lua-resty-http/0.16.1 (Lua) ngx_lua/9014\\r\\n\\r\\n\",\n"
                + "\t\"http_version\": 1.1,\n" + "\t\"request_module\": \"product_detail_info\"\n" + "}]";

        JSONArray jsArray = JSONArray.parseArray(message);

        String message1 = "{\"request_module\":\"product_detail_info\",\"raw_reader\":\"GET \\/product?productId=7&shopId=1 HTTP\\/1.1\\r\\nHost: 192.168.133.133\\r\\nUser-Agent: lua-resty-http\\/0.16.1 (Lua) ngx_lua\\/9014\\r\\n\\r\\n\",\"http_version\":1.1,\"method\":\"GET\",\"uri_args\":{\"productId\":\"7\",\"shopId\":\"1\"},\"headers\":{\"host\":\"192.168.133.133\",\"user-agent\":\"lua-resty-http\\/0.16.1 (Lua) ngx_lua\\/9014\"}}";
        //将JSON字符串转换成Json对象
        JSONObject messageJSON = JSONObject.parseObject(message1);
        System.out.println("JSON字符串转换成JSONObject:" + messageJSON);

        String message2 = JSON.toJSONString(messageJSON);
        System.out.println("JSONObject转换成String:" + message2);

        JSONObject uriArgsJSON = messageJSON.getJSONObject("uri_args");
        Long productId = uriArgsJSON.getLong("productId");
        System.out.println("商品Id:"+ productId);

    }
}
