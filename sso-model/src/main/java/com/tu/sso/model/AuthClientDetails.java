package com.tu.sso.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * @Auther: tuyongjian
 * @Date: 2019/10/24 09:48
 * @Description: 客户端实体
 */
public class AuthClientDetails {

    //@ApiModelProperty(value = "自增id", name = "id",required = true)
    private Integer id;

    //@ApiModelProperty(value = "客户端id", name = "clientId", required = true)
    private String clientId; //

    //@ApiModelProperty(value = "客户端密码", name = "clientSecret", required = true)
    private String clientSecret; //

    //@ApiModelProperty(value = "资源范围 传值格式示例 auth,audit", name = "resourceIds")
    private String resourceIds; //

    //@ApiModelProperty(value = "资源范围 传值格式示例 auth，audit", name = "scopes")
    //用来限制客户端的访问范围，如果为空（默认）的话，那么客户端拥有全部的访问范围
    //scope中文翻译就是作用域，用来限制客户端权限访问的范围，可以用来设置角色或者权限，也可以不设置
    private String scopes; //

    //@ApiModelProperty(value = "授权类型 （四种多选或单选） 传值示例 password,authorization_code,client_credentials,refresh_token", name = "authorizedGrantTypes", required = true)
    private String authorizedGrantTypes; //

    //@ApiModelProperty(value = "code返回地址  示例（url为返回地址） url1,url2", name = "webServerRedirectUris")
    private String webServerRedirectUris; //

    //@ApiModelProperty(value = "权限范围 示例 auth，audit", name = "authorities")
    private String authorities; //

    //@ApiModelProperty(value = "token有效时间 秒", name = "accessTokenValidity",required = true)
    private Integer accessTokenValidity; //

    //@ApiModelProperty(value = "刷新token有效时间 秒", name = "refreshTokenValidity",required = true)
    private Integer refreshTokenValidity; //

    //@ApiModelProperty(hidden = true)
    private String additionalInformation; //

    //@ApiModelProperty(hidden = true)
    private String autoApprove;  // 是否自动授权  默认false

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(String resourceIds) {
        this.resourceIds = resourceIds;
    }

    public String getScopes() {
        return scopes;
    }

    public void setScopes(String scopes) {
        this.scopes = scopes;
    }

    public String getAuthorizedGrantTypes() {
        return authorizedGrantTypes;
    }

    public void setAuthorizedGrantTypes(String authorizedGrantTypes) {
        this.authorizedGrantTypes = authorizedGrantTypes;
    }

    public String getWebServerRedirectUris() {
        return webServerRedirectUris;
    }

    public void setWebServerRedirectUris(String webServerRedirectUris) {
        this.webServerRedirectUris = webServerRedirectUris;
    }

    public String getAuthorities() {
        return authorities;
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValidity() {
        return accessTokenValidity;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public Integer getRefreshTokenValidity() {
        return refreshTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getAutoApprove() {
        return autoApprove;
    }

    public void setAutoApprove(String autoApprove) {
        this.autoApprove = autoApprove;
    }

}
