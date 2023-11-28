package com.example.boilerplatejavaspringbootapi.restapi.v1.user.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author ardyardyaaan
 */

@Entity
@Table(name = "mst_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "user_id"
        })
})
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_user_gen")
    @SequenceGenerator(name = "mst_user_gen", sequenceName = "user_id_seq", allocationSize = 1)
    @Column(name = "user_id", length = 11, nullable = false)
    private Integer userId;

    @NotBlank
    @Column(name = "email", length = 100, nullable = false)
    private String email;

    @NotBlank
    @Column(name = "password", length = 255, nullable = false)
    private String password;

    @Column(name = "token", length = 255, nullable = true)
    @Type(type = "text")
    private String token;

    @Column(name = "token_expired", length = 25, nullable = true)
    private String tokenExpired;

    @Column(name = "fullname", length = 100, nullable = true)
    private String fullname;

    @NotBlank
    @Column(name = "start_date", length = 25, nullable = false)
    private String startDate;

    @NotBlank
    @Column(name = "end_date", length = 25, nullable = false)
    private String endDate;

    @NotNull
    @Column(name = "status", length = 11, nullable = false, columnDefinition = "integer default 1")
    private Integer status;

    @NotNull
    @Column(name = "is_reset_password", length = 11, nullable = false, columnDefinition = "integer default 1")
    private Integer isResetPassword;

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
    @Column(name = "updated_at", insertable = false, updatable = true, nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    public UserEntity() {

    }

    public UserEntity(@NotBlank String email, @NotBlank String password, String fullname, @NotBlank String startDate,
            @NotBlank String endDate, @NotNull Integer status, @NotNull Integer isResetPassword,
            @NotNull Integer createdBy, Date createdAt) {
        this.email = email;
        this.password = password;
        this.fullname = fullname;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.isResetPassword = isResetPassword;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenExpired() {
        return tokenExpired;
    }

    public void setTokenExpired(String tokenExpired) {
        this.tokenExpired = tokenExpired;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsResetPassword() {
        return isResetPassword;
    }

    public void setIsResetPassword(Integer isResetPassword) {
        this.isResetPassword = isResetPassword;
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
