import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @author zhanzhan
 * @date 2020/12/7 22:09
 */
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
    }
}
