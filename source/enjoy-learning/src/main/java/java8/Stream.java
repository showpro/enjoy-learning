package java8;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import common.entity.Users;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Description Java8新特性 stream流常用方法
 * @Author zhanzhan
 * @Date 2020/10/19 18:58
 */
public class Stream {
    public static void main(String[] args) {
        filter();
        sorted();
        map();
        flatmap();
        allMatch();
        anyMatch();
        join();
        group();
        groupCount();
    }
    public static List<Users> users(){
        List<Users> list = Arrays.asList(
                new Users("李星云", 18, 0, "渝州",new BigDecimal(1000)),
                new Users("陆林轩", 16, 1, "渝州",new BigDecimal(500)),
                new Users("姬如雪", 17, 1, "幻音坊",new BigDecimal(800)),
                new Users("袁天罡", 99, 0, "藏兵谷",new BigDecimal(100000)),
                new Users("张子凡", 19, 0, "天师府",new BigDecimal(900)),
                new Users("陆佑劫", 45, 0, "不良人",new BigDecimal(600)),
                new Users("张天师", 48, 0, "天师府",new BigDecimal(1100)),
                new Users("蚩梦", 18, 1, "万毒窟",new BigDecimal(800))
        );
        return list;
    }

    /*filter过滤(T-> boolean)*/
    public static void filter(){
        List<Users> list = users();
        List<Users> newlist = list.stream().filter(user -> user.getAge() > 20)
                .collect(Collectors.toList());
        System.out.println("------结果------");
        for (Users user : newlist) {
            System.out.println(user.getName()+" --> "+ user.getAge());
        }
    }

    /*sorted排序*/
    public static void sorted(){
        List<Users> list = users();
        List<Users> newlist = list.stream()
                .sorted(Comparator.comparingInt(Users::getAge))
                .collect(Collectors.toList());
        System.out.println("------结果------");
        for (Users user : newlist) {
            System.out.println(user.getName()+" --> "+ user.getAge());
        }
    }

    /*map(T->R)*/
    public static void map(){
        List<Users> list = users();
        List<String> newlist = list.stream()
                .map(Users::getName).distinct().collect(Collectors.toList());
        System.out.println("------结果------");
        for (String add : newlist) {
            System.out.println(add);
        }
    }

    /*flatMap(T -> Stream<R>)*/
    public static void flatmap(){
        List<String> flatmap = new ArrayList<>();
        flatmap.add("常宣灵,常昊灵");
        flatmap.add("孟婆,判官红,判官蓝");
        /*
            这里原集合中的数据由逗号分割，使用split进行拆分后，得到的是Stream<String[]>，
            字符串数组组成的流，要使用flatMap的Arrays::stream
            将Stream<String[]>转为Stream<String>,然后把流相连接
        */
        flatmap = flatmap.stream()
                .map(s -> s.split(","))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());
        for (String name : flatmap) {
            System.out.println(name);
        }
    }

    /*allMatch（T->boolean）检测是否全部满足参数行为*/
    public static void allMatch(){
        List<Users> list = users();
        boolean flag = list.stream()
                .allMatch(user -> user.getAge() >= 17);
        System.out.println(flag);
    }

    /*anyMatch（T->boolean）检测是否有任意元素满足给定的条件*/
    public static void anyMatch(){
        List<Users> list = users();
        boolean flag = list.stream()
                .anyMatch(user -> user.getSex() == 1);
        System.out.println(flag);
    }

    /*拼接*/
    public static void join(){
        List<Users> list = users();
        String names = list.stream()
                .map(Users::getName)
                .collect(Collectors.joining(", "));
        System.out.println(names);
    }

    /*分组*/
    public static void group(){
        Map<Integer, List<Users>> map = users().stream()
                .collect(Collectors.groupingBy(Users::getSex));
        System.out.println(JSON.toJSONString(map));
        System.out.println();
        Map<Integer, Map<Integer,List<Users>>> map2 = users().stream()
                .collect(Collectors.groupingBy(Users::getSex,
                        Collectors.groupingBy(Users::getAge)));
        System.out.println(JSON.toJSONString(map2));
    }

    /*分组合计*/
    public static void groupCount(){
        Map<Integer, Long> num = users().stream()
                .collect(Collectors.groupingBy(Users::getSex, Collectors.counting()));
        System.out.println(num);


        Map<Integer, Long> num2 = users().stream()
                .filter(user -> user.getAge()>=18)
                .collect(Collectors.groupingBy(Users::getSex, Collectors.counting()));
        System.out.println(num2);
    }
}
