package bcy.dao;

public class User {
    private Long id;
    private String name;
    private String pwd;
    private String email;
    private String sign;
    private Integer level;

    // 粉丝数
    private Long following;
    private long followers;

    public Long getFollowing() {
        return following;
    }

    public void setFollowing(Long following) {
        this.following = following;
    }

    public long getFollowers() {
        return followers;
    }

    public void setFollowers(long followers) {
        this.followers = followers;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPwd() {
        return pwd;
    }

    public String getEmail() {
        return email;
    }

    public String getSign() {
        return sign;
    }

    public Integer getLevel() {
        return level;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

}
