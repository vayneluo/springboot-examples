package com.haizol.batch.entity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "db_user")
public class User {
    /**
     * 用户表id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 姓名
     */
    @Column(name = "name")
    private String name;

    /**
     * 联系人（英文）
     */
    @Column(name = "enName")
    private String enName;

    /**
     * 联系人邮箱  就是用户的登陆账户
     */
    @Column(name = "email")
    private String email;

    /**
     * 称谓  CodeList表中的type为sex
     */
    @Column(name = "salutation")
    private Integer salutation;

    /**
     * 电话
     */
    @Column(name = "tel")
    private String tel;

    /**
     * 传真
     */
    @Column(name = "fax")
    private String fax;

    /**
     * 密码
     */
    @Column(name = "password")
    private String password;

    /**
     * 职位
     */
    @Column(name = "position")
    private String position;

    /**
     * 职务（英文）
     */
    @Column(name = "enPosition")
    private String enPosition;

    /**
     * 国家ID 对应国家表的外键 country
     */
    @Column(name = "stateId")
    private Integer stateId;

    /**
     * 省份ID 对应省份表的外键
     */
    @Column(name = "provinceId")
    private Integer provinceId;

    /**
     * 城市ID 对应城市表的外键
     */
    @Column(name = "cityId")
    private Integer cityId;

    /**
     * 头像图片地址
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 网址
     */
    @Column(name = "site")
    private String site;

    /**
     * 手机号码
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 公司ID 对应公司表的外键
     */
    @Column(name = "compId")
    private Long compId;

    /**
     * 是否短信验证  默认 0（0 未通过 1 通过） CodeList表中的type为issms
     */
    @Column(name = "isSmsValidation")
    private Integer isSmsValidation;

    /**
     * 是否主账户 默认1（0 子账户 1 管理员）
	 * CodeList表中的type为isadmin
     */
    @Column(name = "isAdmin")
    private Integer isAdmin;

    /**
     * 是否删除 默认 0（0 未删除 1 删除） CodeList表中的type为isdel
     */
    @Column(name = "isDel")
    private Integer isDel;

    /**
     * 创建时间 注册的时间
     */
    @Column(name = "createTime")
    private Date createTime;

    /**
     * 是否可创建询盘   默认1   0否 1是(采购商用户)CodeList表中的type为createrfq
     */
    @Column(name = "enableCreateRfq")
    private Integer enableCreateRfq;

    /**
     * 是否可提交询盘  默认0   0 否 1是(采购商用户)CodeList表中的type为submitRfq
     */
    @Column(name = "enableSubmitRfq")
    private Integer enableSubmitRfq;

    /**
     * 是否可创建报价 默认1   0否 1是(供应商用户)CodeList表中的type为createquote
     */
    @Column(name = "enableCreateQuote")
    private Integer enableCreateQuote;

    /**
     * 是否可提交报价 默认0   0否 1是(供应商用户) CodeList表中的type为submitquote
     */
    @Column(name = "enableSubmitQuote")
    private Integer enableSubmitQuote;

    /**
     * 是否显示联系人  默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    @Column(name = "showName")
    private Integer showName;

    /**
     * 是否显示职位 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    @Column(name = "showPosit")
    private Integer showPosit;

    /**
     * 是否显示地址 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    @Column(name = "showAdd")
    private Integer showAdd;

    /**
     * 是否显示电话 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    @Column(name = "showTel")
    private Integer showTel;

    /**
     * 是否显示传真 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    @Column(name = "showFax")
    private Integer showFax;

    /**
     * 是否显示网站 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    @Column(name = "showWeb")
    private Integer showWeb;

    /**
     * 是否显示手机号 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    @Column(name = "showPhone")
    private Integer showPhone;

    /**
     * 最后登录时间
     */
    @Column(name = "lastLogin")
    private Date lastLogin;

    /**
     * 最后读取邮件时间
     */
    @Column(name = "lastReadEmail")
    private Date lastReadEmail;

    /**
     * 最后操作询盘时间
     */
    @Column(name = "lastOperateRfq")
    private Date lastOperateRfq;

    /**
     * 最后备注时间
     */
    @Column(name = "lastComment")
    private Date lastComment;

    /**
     * 账户状态
     */
    @Column(name = "states")
    private Integer states;

    /**
     * 备用邮箱
     */
    @Column(name = "backupEmail")
    private String backupEmail;

    /**
     * 设置用户是否接受邮件 默认1 1,可以 0,不可以
     */
    @Column(name = "acceptEmail")
    private Integer acceptEmail;

    /**
     * 获取用户表id
     *
     * @return id - 用户表id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置用户表id
     *
     * @param id 用户表id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * 获取联系人（英文）
     *
     * @return enName - 联系人（英文）
     */
    public String getEnName() {
        return enName;
    }

    /**
     * 设置联系人（英文）
     *
     * @param enName 联系人（英文）
     */
    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }

    /**
     * 获取联系人邮箱  就是用户的登陆账户
     *
     * @return email - 联系人邮箱  就是用户的登陆账户
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置联系人邮箱  就是用户的登陆账户
     *
     * @param email 联系人邮箱  就是用户的登陆账户
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * 获取称谓  CodeList表中的type为sex
     *
     * @return salutation - 称谓  CodeList表中的type为sex
     */
    public Integer getSalutation() {
        return salutation;
    }

    /**
     * 设置称谓  CodeList表中的type为sex
     *
     * @param salutation 称谓  CodeList表中的type为sex
     */
    public void setSalutation(Integer salutation) {
        this.salutation = salutation;
    }

    /**
     * 获取电话
     *
     * @return tel - 电话
     */
    public String getTel() {
        return tel;
    }

    /**
     * 设置电话
     *
     * @param tel 电话
     */
    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    /**
     * 获取传真
     *
     * @return fax - 传真
     */
    public String getFax() {
        return fax;
    }

    /**
     * 设置传真
     *
     * @param fax 传真
     */
    public void setFax(String fax) {
        this.fax = fax == null ? null : fax.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取职位
     *
     * @return position - 职位
     */
    public String getPosition() {
        return position;
    }

    /**
     * 设置职位
     *
     * @param position 职位
     */
    public void setPosition(String position) {
        this.position = position == null ? null : position.trim();
    }

    /**
     * 获取职务（英文）
     *
     * @return enPosition - 职务（英文）
     */
    public String getEnPosition() {
        return enPosition;
    }

    /**
     * 设置职务（英文）
     *
     * @param enPosition 职务（英文）
     */
    public void setEnPosition(String enPosition) {
        this.enPosition = enPosition == null ? null : enPosition.trim();
    }

    /**
     * 获取国家ID 对应国家表的外键 country
     *
     * @return stateId - 国家ID 对应国家表的外键 country
     */
    public Integer getStateId() {
        return stateId;
    }

    /**
     * 设置国家ID 对应国家表的外键 country
     *
     * @param stateId 国家ID 对应国家表的外键 country
     */
    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }

    /**
     * 获取省份ID 对应省份表的外键
     *
     * @return provinceId - 省份ID 对应省份表的外键
     */
    public Integer getProvinceId() {
        return provinceId;
    }

    /**
     * 设置省份ID 对应省份表的外键
     *
     * @param provinceId 省份ID 对应省份表的外键
     */
    public void setProvinceId(Integer provinceId) {
        this.provinceId = provinceId;
    }

    /**
     * 获取城市ID 对应城市表的外键
     *
     * @return cityId - 城市ID 对应城市表的外键
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 设置城市ID 对应城市表的外键
     *
     * @param cityId 城市ID 对应城市表的外键
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取头像  图片地址
     *
     * @return avatar - 头像  图片地址
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置头像  图片地址
     *
     * @param avatar 头像  图片地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * 获取网址
     *
     * @return site - 网址
     */
    public String getSite() {
        return site;
    }

    /**
     * 设置网址
     *
     * @param site 网址
     */
    public void setSite(String site) {
        this.site = site == null ? null : site.trim();
    }

    /**
     * 获取手机号码
     *
     * @return mobile - 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号码
     *
     * @param mobile 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * 获取公司ID 对应公司表的外键
     *
     * @return compId - 公司ID 对应公司表的外键
     */
    public Long getCompId() {
        return compId;
    }

    /**
     * 设置公司ID 对应公司表的外键
     *
     * @param compId 公司ID 对应公司表的外键
     */
    public void setCompId(Long compId) {
        this.compId = compId;
    }

    /**
     * 获取是否短信验证  默认 0（0 未通过 1 通过） CodeList表中的type为issms
     *
     * @return isSmsValidation - 是否短信验证  默认 0（0 未通过 1 通过） CodeList表中的type为issms
     */
    public Integer getIsSmsValidation() {
        return isSmsValidation;
    }

    /**
     * 设置是否短信验证  默认 0（0 未通过 1 通过） CodeList表中的type为issms
     *
     * @param isSmsValidation 是否短信验证  默认 0（0 未通过 1 通过） CodeList表中的type为issms
     */
    public void setIsSmsValidation(Integer isSmsValidation) {
        this.isSmsValidation = isSmsValidation;
    }

    /**
     * 获取是否主账户 默认1（0 子账户 1 管理员）
CodeList表中的type为isadmin
     *
     * @return isAdmin - 是否主账户 默认1（0 子账户 1 管理员）
CodeList表中的type为isadmin
     */
    public Integer getIsAdmin() {
        return isAdmin;
    }

    /**
     * 设置是否主账户 默认1（0 子账户 1 管理员）
CodeList表中的type为isadmin
     *
     * @param isAdmin 是否主账户 默认1（0 子账户 1 管理员）
CodeList表中的type为isadmin
     */
    public void setIsAdmin(Integer isAdmin) {
        this.isAdmin = isAdmin;
    }

    /**
     * 获取是否删除 默认 0（0 未删除 1 删除） CodeList表中的type为isdel
     *
     * @return isDel - 是否删除 默认 0（0 未删除 1 删除） CodeList表中的type为isdel
     */
    public Integer getIsDel() {
        return isDel;
    }

    /**
     * 设置是否删除 默认 0（0 未删除 1 删除） CodeList表中的type为isdel
     *
     * @param isDel 是否删除 默认 0（0 未删除 1 删除） CodeList表中的type为isdel
     */
    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    /**
     * 获取创建时间 注册的时间
     *
     * @return createTime - 创建时间 注册的时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间 注册的时间
     *
     * @param createTime 创建时间 注册的时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取是否可创建询盘   默认1   0否 1是(采购商用户)CodeList表中的type为createrfq
     *
     * @return enableCreateRfq - 是否可创建询盘   默认1   0否 1是(采购商用户)CodeList表中的type为createrfq
     */
    public Integer getEnableCreateRfq() {
        return enableCreateRfq;
    }

    /**
     * 设置是否可创建询盘   默认1   0否 1是(采购商用户)CodeList表中的type为createrfq
     *
     * @param enableCreateRfq 是否可创建询盘   默认1   0否 1是(采购商用户)CodeList表中的type为createrfq
     */
    public void setEnableCreateRfq(Integer enableCreateRfq) {
        this.enableCreateRfq = enableCreateRfq;
    }

    /**
     * 获取是否可提交询盘  默认0   0 否 1是(采购商用户)CodeList表中的type为submitRfq
     *
     * @return enableSubmitRfq - 是否可提交询盘  默认0   0 否 1是(采购商用户)CodeList表中的type为submitRfq
     */
    public Integer getEnableSubmitRfq() {
        return enableSubmitRfq;
    }

    /**
     * 设置是否可提交询盘  默认0   0 否 1是(采购商用户)CodeList表中的type为submitRfq
     *
     * @param enableSubmitRfq 是否可提交询盘  默认0   0 否 1是(采购商用户)CodeList表中的type为submitRfq
     */
    public void setEnableSubmitRfq(Integer enableSubmitRfq) {
        this.enableSubmitRfq = enableSubmitRfq;
    }

    /**
     * 获取是否可创建报价 默认1   0否 1是(供应商用户)CodeList表中的type为createquote
     *
     * @return enableCreateQuote - 是否可创建报价 默认1   0否 1是(供应商用户)CodeList表中的type为createquote
     */
    public Integer getEnableCreateQuote() {
        return enableCreateQuote;
    }

    /**
     * 设置是否可创建报价 默认1   0否 1是(供应商用户)CodeList表中的type为createquote
     *
     * @param enableCreateQuote 是否可创建报价 默认1   0否 1是(供应商用户)CodeList表中的type为createquote
     */
    public void setEnableCreateQuote(Integer enableCreateQuote) {
        this.enableCreateQuote = enableCreateQuote;
    }

    /**
     * 获取是否可提交报价 默认0   0否 1是(供应商用户) CodeList表中的type为submitquote
     *
     * @return enableSubmitQuote - 是否可提交报价 默认0   0否 1是(供应商用户) CodeList表中的type为submitquote
     */
    public Integer getEnableSubmitQuote() {
        return enableSubmitQuote;
    }

    /**
     * 设置是否可提交报价 默认0   0否 1是(供应商用户) CodeList表中的type为submitquote
     *
     * @param enableSubmitQuote 是否可提交报价 默认0   0否 1是(供应商用户) CodeList表中的type为submitquote
     */
    public void setEnableSubmitQuote(Integer enableSubmitQuote) {
        this.enableSubmitQuote = enableSubmitQuote;
    }

    /**
     * 获取是否显示联系人  默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @return showName - 是否显示联系人  默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public Integer getShowName() {
        return showName;
    }

    /**
     * 设置是否显示联系人  默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @param showName 是否显示联系人  默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public void setShowName(Integer showName) {
        this.showName = showName;
    }

    /**
     * 获取是否显示职位 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @return showPosit - 是否显示职位 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public Integer getShowPosit() {
        return showPosit;
    }

    /**
     * 设置是否显示职位 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @param showPosit 是否显示职位 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public void setShowPosit(Integer showPosit) {
        this.showPosit = showPosit;
    }

    /**
     * 获取是否显示地址 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @return showAdd - 是否显示地址 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public Integer getShowAdd() {
        return showAdd;
    }

    /**
     * 设置是否显示地址 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @param showAdd 是否显示地址 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public void setShowAdd(Integer showAdd) {
        this.showAdd = showAdd;
    }

    /**
     * 获取是否显示电话 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @return showTel - 是否显示电话 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public Integer getShowTel() {
        return showTel;
    }

    /**
     * 设置是否显示电话 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @param showTel 是否显示电话 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public void setShowTel(Integer showTel) {
        this.showTel = showTel;
    }

    /**
     * 获取是否显示传真 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @return showFax - 是否显示传真 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public Integer getShowFax() {
        return showFax;
    }

    /**
     * 设置是否显示传真 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @param showFax 是否显示传真 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public void setShowFax(Integer showFax) {
        this.showFax = showFax;
    }

    /**
     * 获取是否显示网站 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @return showWeb - 是否显示网站 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public Integer getShowWeb() {
        return showWeb;
    }

    /**
     * 设置是否显示网站 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @param showWeb 是否显示网站 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public void setShowWeb(Integer showWeb) {
        this.showWeb = showWeb;
    }

    /**
     * 获取是否显示手机号 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @return showPhone - 是否显示手机号 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public Integer getShowPhone() {
        return showPhone;
    }

    /**
     * 设置是否显示手机号 默认0 （0 不显示 1显示）CodeList表中的type为show
     *
     * @param showPhone 是否显示手机号 默认0 （0 不显示 1显示）CodeList表中的type为show
     */
    public void setShowPhone(Integer showPhone) {
        this.showPhone = showPhone;
    }

    /**
     * 获取最后登录时间
     *
     * @return lastLogin - 最后登录时间
     */
    public Date getLastLogin() {
        return lastLogin;
    }

    /**
     * 设置最后登录时间
     *
     * @param lastLogin 最后登录时间
     */
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    /**
     * 获取最后读取邮件时间
     *
     * @return lastReadEmail - 最后读取邮件时间
     */
    public Date getLastReadEmail() {
        return lastReadEmail;
    }

    /**
     * 设置最后读取邮件时间
     *
     * @param lastReadEmail 最后读取邮件时间
     */
    public void setLastReadEmail(Date lastReadEmail) {
        this.lastReadEmail = lastReadEmail;
    }

    /**
     * 获取最后操作询盘时间
     *
     * @return lastOperateRfq - 最后操作询盘时间
     */
    public Date getLastOperateRfq() {
        return lastOperateRfq;
    }

    /**
     * 设置最后操作询盘时间
     *
     * @param lastOperateRfq 最后操作询盘时间
     */
    public void setLastOperateRfq(Date lastOperateRfq) {
        this.lastOperateRfq = lastOperateRfq;
    }

    /**
     * 获取最后备注时间
     *
     * @return lastComment - 最后备注时间
     */
    public Date getLastComment() {
        return lastComment;
    }

    /**
     * 设置最后备注时间
     *
     * @param lastComment 最后备注时间
     */
    public void setLastComment(Date lastComment) {
        this.lastComment = lastComment;
    }

    /**
     * 获取账户状态
     *
     * @return states - 账户状态
     */
    public Integer getStates() {
        return states;
    }

    /**
     * 设置账户状态
     *
     * @param states 账户状态
     */
    public void setStates(Integer states) {
        this.states = states;
    }

    /**
     * 获取备用邮箱
     *
     * @return backupEmail - 备用邮箱
     */
    public String getBackupEmail() {
        return backupEmail;
    }

    /**
     * 设置备用邮箱
     *
     * @param backupEmail 备用邮箱
     */
    public void setBackupEmail(String backupEmail) {
        this.backupEmail = backupEmail == null ? null : backupEmail.trim();
    }

    /**
     * 获取设置用户是否接受邮件 默认1 1,可以 0,不可以
     *
     * @return acceptEmail - 设置用户是否接受邮件 默认1 1,可以 0,不可以
     */
    public Integer getAcceptEmail() {
        return acceptEmail;
    }

    /**
     * 设置设置用户是否接受邮件 默认1 1,可以 0,不可以
     *
     * @param acceptEmail 设置用户是否接受邮件 默认1 1,可以 0,不可以
     */
    public void setAcceptEmail(Integer acceptEmail) {
        this.acceptEmail = acceptEmail;
    }
}