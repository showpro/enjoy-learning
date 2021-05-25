package spring.test;

/**
 * @author zhanzhan
 * @date 2021/4/16 22:14
 */
public class UserController {

    //使用自定义的Autowired实现属性注入
    @Autowired
    private UserService userService;
    /**
     * 只是成员变量不想注入
     */
    private Integer integer;

    //getter setter
    public UserService getUserService() {
        return userService;
    }
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

}
