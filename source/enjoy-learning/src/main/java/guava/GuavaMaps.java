package guava;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Data;
import util.MapperUtil;

import java.util.ArrayList;

/**
 * @Description
 * @Author zhanzhan
 * @Date 2020/10/12 8:31
 */
public class GuavaMaps {

    /**
     * Maps.uniqueIndex(Iterable, Function)将列表转换为map,有一大堆对象，每个对象都有一些独特的属性，能够根据该独特属性查找到对应对象。
     *
     * 必须确保key的唯一性，否则会报错
     *
     */
    public static void main(String[] args) {
        // nickname属性能唯一确定一个WebUser
        ArrayList<WebUser> users = Lists.newArrayList(new WebUser(1, "小三"), new WebUser(2, "小四"), new WebUser(1, "小五"), new WebUser(1, "小六"));
        // 得到以nickname为key，WebUser为值的一个map
//        ImmutableMap<String, WebUser> map = Maps.uniqueIndex(users, new Function<WebUser, String>() {
//            @Override
//            public String apply(WebUser user) {
//                return user.getNickname();
//            }
//        });

        /* 可以进一步简化 */
        ImmutableMap<String, WebUser> map = Maps.uniqueIndex(users,WebUser::getNickname);

        System.err.println("map:" + map);
        System.err.println("name:" + map.get("小四").getNickname());
    }

    @Data
    static class WebUser {
        private Integer sid;
        private String nickname;

        public WebUser(Integer sid, String nickname) {
            this.sid = sid;
            this.nickname = nickname;
        }

    }



}
