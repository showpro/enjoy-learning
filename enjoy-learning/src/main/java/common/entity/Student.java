package common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @Author zhanzhan
 * @Date 2021/5/28 11:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable {

    private static final long serialVersionUID = 5520272418435336665L;

    private String userName;

    private Integer age;

    private String address;

    private String sex;

}
