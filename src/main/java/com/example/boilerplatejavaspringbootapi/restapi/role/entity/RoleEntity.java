package com.example.boilerplatejavaspringbootapi.restapi.role.entity;

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
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "mst_role", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "role_id"
    })
})
public class RoleEntity implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mst_role_gen")
    @SequenceGenerator(name = "mst_role_gen", sequenceName = "role_id_seq", allocationSize = 1)
    @Column(name = "role_id", length = 11, nullable = false)
    private Integer roleId;

    @NotBlank
    @Column(name = "role_name", length = 100, nullable = false)
    private String roleName;

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

    public RoleEntity() {
        
    }

    public RoleEntity(@NotBlank String roleName, @NotBlank String startDate, @NotBlank String endDate,
            @NotNull Integer status, @NotNull Integer createdBy, Date createdAt) {
        this.roleName = roleName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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
