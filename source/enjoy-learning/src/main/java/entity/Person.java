package entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Map;

/**
 * @Description
 * @Author zhanzhan
 * @Date 2020/10/12 10:54
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private Long id;
    private String name;
    //private String email;
    private String pemail;
    private Date birth;
    private User user;
    // 用于存储地址信息
    private Map<String, String> address;
}
