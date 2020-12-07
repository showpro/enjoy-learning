package redis;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description
 * spring-data-redis中的redisTemplate如何调用lua?
 *
 * @Author zhanzhan
 * @Date 2020/10/21 15:36
 */
public class Lua {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public <T> T runLua(String fileClasspath, Class<T> returnType, List<String> keys, Object ... values){
        /*
         * 框架把lua脚本封装成RedisScript对象，并且可以将lua脚本执行的结果自动转换为配置的java类型，
         * 然后只要直接调用execute方法即可
         * 并且这个execute逻辑中封装了evalsha的优化
         */
        DefaultRedisScript<T> redisScript =new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource(fileClasspath)));
        redisScript.setResultType(returnType);
        return stringRedisTemplate.execute(redisScript,keys,values);
    }
}
