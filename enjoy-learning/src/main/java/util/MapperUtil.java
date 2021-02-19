package util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 我在之前使用使用的时候，使用的并不是单例模式，然而造成了内存溢出的情况，
 * 后来采用单例的模式，解决了这个问题。这里单例使用的是枚举方式实现的，也是效率比较高的一种吧
 * 使用：MapperUtil.INSTANCE.map(UserResult.class, user);这样就可以将 User 对象转换成我们需要的 UserResult 对象返回了
 *
 * @Description  映射工具类
 * @Author zhanzhan
 * @Date 2020/10/12 10:12
 */
public enum MapperUtil {

    /**
     * 实例
     */
    INSTANCE;

    /**
     * 默认字段工厂
     */
    private static final MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();

    /**
     * 默认字段实例
     */
    private static final MapperFacade MAPPER_FACADE = MAPPER_FACTORY.getMapperFacade();

    /**
     * 默认字段实例集合
     */
    private static Map<String, MapperFacade> CACHE_MAPPER_FACADE_MAP = new ConcurrentHashMap<>();

    /**
     * 映射实体（默认字段）
     * 这种映射就是DTO字段名称和实体对象DO之间字段名称一致
     *
     * @param toClass targetType 映射类对象 DTO对象
     * @param data    source 数据（对象）DO对象
     * @return 映射类对象
     */
    public <E, T> E map(Class<E> toClass, T data) {
        return MAPPER_FACADE.map(data, toClass);
    }

    /**
     * 映射实体（自定义配置）
     *
     * @param toClass   targetType 映射类对象 DTO对象
     * @param data      source 数据（对象）DO对象
     * @param configMap 自定义配置 这个自定义配置我目前就理解为，如果需要映射的字段之间存在差异，需要通过一个对象来中转
     * @return 映射类对象
     */
    public <E, T> E map(Class<E> toClass, T data, Map<String, String> configMap) {
        MapperFacade mapperFacade = this.getMapperFacade(toClass, data.getClass(), configMap);
        return mapperFacade.map(data, toClass);
    }

    /**
     * 映射集合（默认字段）
     * 映射为集合的形式
     *
     * @param toClass targetType 映射类对象 DTO对象
     * @param data    source 数据（集合） DO对象
     * @return 映射类对象
     */
    public <E, T> List<E> mapAsList(Class<E> toClass, Collection<T> data) {
        return MAPPER_FACADE.mapAsList(data, toClass);
    }

    /**
     * 获取自定义映射
     * 如何自定义字段映射，DO和DTO之间字段有着明显的差异，如果通过属性获取方式来映射，这就得说起Orika的Field属性了，获取这个高级特性的方法如下：
     *
     * @param toClass   映射类
     * @param dataClass 数据映射类
     * @param configMap 自定义配置
     * @return 映射类对象
     */
    private <E, T> MapperFacade getMapperFacade(Class<E> toClass, Class<T> dataClass, Map<String, String> configMap) {
        String mapKey = dataClass.getCanonicalName() + "_" + toClass.getCanonicalName();
        MapperFacade mapperFacade = CACHE_MAPPER_FACADE_MAP.get(mapKey);
        if (Objects.isNull(mapperFacade)) {
            MapperFactory factory = new DefaultMapperFactory.Builder().build();
            ClassMapBuilder classMapBuilder = factory.classMap(dataClass, toClass);
            configMap.forEach(classMapBuilder::field);
            classMapBuilder.byDefault().register();
            mapperFacade = factory.getMapperFacade();
            CACHE_MAPPER_FACADE_MAP.put(mapKey, mapperFacade);
        }
        return mapperFacade;
    }
}
