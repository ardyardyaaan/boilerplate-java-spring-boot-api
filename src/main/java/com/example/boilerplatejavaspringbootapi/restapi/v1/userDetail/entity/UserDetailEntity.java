package com.example.boilerplatejavaspringbootapi.restapi.v1.userDetail.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.boilerplatejavaspringbootapi.restapi.v1.role.entity.RoleEntity;
import com.example.boilerplatejavaspringbootapi.restapi.v1.user.entity.UserEntity;

/**
 *
 * @author ardyardyaaan
 */

@Entity
@Table(name = "trx_user_detail", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "user_detail_id"
        })
})
public class UserDetailEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "trx_user_detail_gen")
    @SequenceGenerator(name = "trx_user_detail_gen", sequenceName = "user_detail_id_seq", allocationSize = 1)
    @Column(name = "user_detail_id", length = 11, nullable = false)
    private Integer userIDetaild;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserEntity userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    private RoleEntity roleId;

    @NotNull
    @Column(name = "status", length = 11, nullable = false, columnDefinition = "integer default 1")
    private Integer status;

    @NotNull
    @Column(name = "created_by", length = 25, nullable = false)
    private Integer createdBy;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updated_by", length = 25, nullable = true)
    private Integer updatedBy;

    @UpdateTimestamp
    @Column(name = "updated_at", insertable = true, updatable = true, nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public UserDetailEntity() {

    }

    public UserDetailEntity(UserEntity userId, RoleEntity roleId, @NotNull Integer status, @NotNull Integer createdBy,
            Date createdAt) {
        this.userId = userId;
        this.roleId = roleId;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Integer getUserIDetaild() {
        return userIDetaild;
    }

    public void setUserIDetaild(Integer userIDetaild) {
        this.userIDetaild = userIDetaild;
    }

    public UserEntity getUserId() {
        return userId;
    }

    public void setUserId(UserEntity userId) {
        this.userId = userId;
    }

    public RoleEntity getRoleId() {
        return roleId;
    }

    public void setRoleId(RoleEntity roleId) {
        this.roleId = roleId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Integer getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Integer updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

}
