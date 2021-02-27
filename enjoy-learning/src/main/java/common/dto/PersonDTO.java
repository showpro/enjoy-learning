package common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @Description DTO对象
 * @Author zhanzhan
 * @Date 2020/10/12 10:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {
    private Long id;
    private String name;
    private String sex;
    private String email;
    /**
     * 对应 Person.user.age
     */
    private Integer age;
    /**
     * 与 DO 里面的字段名称(birthDay)不一致
     */
    private Date birth;
    /**
     * 对 DO 里面的字段(birthDay)进行拓展,dateFormat 的形式
     */
    private String birthDateFormat;
    /**
     * 对 DO 里面的字段(birthDay)进行拓展,expression 的形式
     */
    private String birthExpressionFormat;

    /**
     *公司地址
     */
    private String company;
    /**
     * 家庭住址
     */
    private String home;
}
