import com.google.common.collect.Maps;
import dto.PersonDTO;
import entity.Person;
import entity.User;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import org.junit.Test;
import util.MapperUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description   博客地址：https://blog.csdn.net/hanerer1314/article/details/94337178
 * @Author zhanzhan
 * @Date 2020/10/12 10:58
 */
public class MapperUtilTest {

    /**
     * 对象之间默认字段映射
     */
    @Test
    public void Test1() {
        //先创建一个实体对象DO class
        Person person = new Person(1200L, "高富帅", "gaofushuai@163.com", new Date(), new User(25), null);
        //对象之间默认字段映射
        PersonDTO personDTO = MapperUtil.INSTANCE.map(PersonDTO.class, person);
        //输出结果
        System.out.println("原始对象：" + person);
        System.out.println("目标对象：" + personDTO);
    }

    /**
     * 自定义字段映射
     * 要测试pemail 和 email之间的映射
     */
    @Test
    public void Test2() {
        // 要测试pemail 和email之间的映射,在Person DO实体对象中修改email属性为pemail
        Person person = new Person(1200L, "高富帅", "gaofushuai@163.com", new Date(), new User(25), null);
        // key 是数据对象 value 是映射对象
        Map<String, String> map = Maps.newHashMap();
        map.put("birth", "birth");
        map.put("pemail", "email");
        map.put("user.age", "age"); // 嵌套映射
        PersonDTO dto = MapperUtil.INSTANCE.map(PersonDTO.class, person, map);

        //输出结果
        System.out.println("原始对象：" + person);
        System.out.println("目标对象：" + dto);
    }

    /**
     * 自定义字段映射
     * 如果需要从一个Map对象中映射到另一个对象的属性中，就需要我们使用类似下标索引的方式构建field字段，
     * 首先我们在Person对象中增加一个Map集合，
     */
    @Test
    public void Test3() {
        // 从一个Map对象中映射到另一个对象的属性中
        Map<String, String> address = new HashMap<>();
        address.put("company", "来广营");
        address.put("home", "望京");

        Person person = new Person(1200L, "高富帅", "gaofushuai@163.com", new Date(), new User(25), address);
        // field字段转换过程：
        MapperFactory factory = new DefaultMapperFactory.Builder().build();
        ClassMapBuilder classMapBuilder = factory.classMap(Person.class, PersonDTO.class)
                .field("address['company']", "company")
                //.exclude("") 如果需要排除指定的某个字段不用映射，在classMap()方法后直接调用exclude("指定字段")即可。
                .field("address['home']", "home");
        classMapBuilder.byDefault().register();
        MapperFacade mapperFacade = factory.getMapperFacade();
        PersonDTO dto = mapperFacade.map(person, PersonDTO.class);
        //PersonDTO dto = MapperUtil.MAPPER_FACADE.map(person, PersonDTO.class);这种方式不行

        //输出结果
        System.out.println("原始对象：" + person);
        System.out.println("目标对象：" + dto);
    }


}
