package com.bruis.common.backend.model.response;
import java.util.Set;

/**
 * 返回给前端的用户信息视图对象
 * @author LuoHaiYang
 */
public class CurrentUserInfoVo {

    /**
     * 用户姓名
     */
    private String name;

    /**
     * 角色
     */
    private Set<String> roles;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户介绍
     */
    private String introduction;

    public CurrentUserInfoVo(String name, Set<String> roles, String avatar, String introduction) {
        this.name = name;
        this.roles = roles;
        this.avatar = avatar;
        this.introduction = introduction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
