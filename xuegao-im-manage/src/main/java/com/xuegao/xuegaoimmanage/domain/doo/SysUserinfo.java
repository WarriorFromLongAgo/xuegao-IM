package com.xuegao.xuegaoimmanage.domain.doo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * <br/> @PackageName：com.fff.springbootapiseedtest.model.po
 * <br/> @ClassName：SysUserinfo
 * <br/> @Description：
 * <br/> @author：xuegao
 * <br/> @date：2020/3/28 18:29
 */
@TableName("sys_userinfo")
public class SysUserinfo implements Serializable {
    private static final long serialVersionUID = 8778430056221728L;

    @TableId("id")
    @ApiModelProperty(value = "主键", position = 10)
    private Long id;

    @TableField("name")
    @ApiModelProperty(value = "姓名", position = 10)
    private String name;

    @TableField("username")
    @ApiModelProperty(value = "登陆用户名", position = 10)
    private String username;

    @TableField("password")
    @ApiModelProperty(value = "登陆密码", position = 10)
    private String password;

    @TableField("status")
    @ApiModelProperty(value = "0正常，1停用", position = 10)
    private String status;

    @TableField("delete_flag")
    @ApiModelProperty(value = "0未删除，1已删除", position = 10)
    private String deleteFlag;

    @TableField("create_id")
    @ApiModelProperty(value = "创建人id", position = 10)
    private Long createId;

    @TableField("create_name")
    @ApiModelProperty(value = "创建人真实名称", position = 10)
    private String createName;

    @TableField("create_time")
    @ApiModelProperty(value = "创建时间", position = 10)
    private Date createTime;

    @TableField("update_id")
    @ApiModelProperty(value = "修改人id", position = 10)
    private Integer updateId;

    @TableField("update_name")
    @ApiModelProperty(value = "修改人真实名称", position = 10)
    private String updateName;

    @TableField("update_time")
    @ApiModelProperty(value = "修改时间", position = 10)
    private Date updateTime;

    public SysUserinfo() {
    }

    @Override
    public String toString() {
        return "SysUserinfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", status='" + status + '\'' +
                ", deleteFlag='" + deleteFlag + '\'' +
                ", createId=" + createId +
                ", createName='" + createName + '\'' +
                ", createTime=" + createTime +
                ", updateId=" + updateId +
                ", updateName='" + updateName + '\'' +
                ", updateTime=" + updateTime +
                '}';
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(String deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Long getCreateId() {
        return createId;
    }

    public void setCreateId(Long createId) {
        this.createId = createId;
    }

    public String getCreateName() {
        return createName;
    }

    public void setCreateName(String createName) {
        this.createName = createName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateId() {
        return updateId;
    }

    public void setUpdateId(Integer updateId) {
        this.updateId = updateId;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SysUserinfo that = (SysUserinfo) o;

        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(username, that.username) &&
                Objects.equals(password, that.password) &&
                Objects.equals(status, that.status) &&
                Objects.equals(deleteFlag, that.deleteFlag) &&
                Objects.equals(createId, that.createId) &&
                Objects.equals(createName, that.createName) &&
                Objects.equals(createTime, that.createTime) &&
                Objects.equals(updateId, that.updateId) &&
                Objects.equals(updateName, that.updateName) &&
                Objects.equals(updateTime, that.updateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, username, password, status, deleteFlag, createId, createName, createTime, updateId, updateName, updateTime);
    }


}