package flowpipline.example01;

/**
 * 用户实体
 * Created by laibao
 */
public class UserEntity {

    /**
     * 用户名
     */
    private String uname ;

    /**
     * 密码
     */
    private String password ;

    /**
     * 验证码
     */
    private String code ;

    /**
     * 用户IP
     */
    private String ip ;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
