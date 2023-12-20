package com.example.boilerplatejavaspringbootapi.restapi.v1.role.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.boilerplatejavaspringbootapi.restapi.v1.role.entity.RoleEntity;



/**
 *
 * @author ardyardyaaan
 */

@Repository
public interface RoleRepository extends PagingAndSortingRepository<RoleEntity, Integer> {

    @Query(value = "select role_id, " +
            "role_name, " +
            "status, " +
            "start_date, " +
            "end_date, " +
            "created_by, " +
            "created_at, " +
            "updated_by, " +
            "updated_at from mst_role " +
            "where lower(role_name) = :roleName " +
            "and status = 1 ", nativeQuery = true)
    RoleEntity findByRoleName(@Param("roleName") String roleName);

    @Query(value = "select role_id, " +
            "role_name, " +
            "status, " +
            "start_date, " +
            "end_date, " +
            "created_by, " +
            "created_at, " +
            "updated_by, " +
            "updated_at from mst_role " +
            "where status = 1 and cast(:today as date) >= cast(start_date as date) " +
            "and cast(:today as date) <= cast(end_date as date)", nativeQuery = true)
    List<RoleEntity> findAllRoleActive(@Param("today") Date today);

    @Query(value = "select role_id, " +
            "role_name, " +
            "status, " +
            "start_date, " +
            "end_date, " +
            "created_by, " +
            "created_at, " +
            "updated_by, " +
            "updated_at from mst_role " +
            "where role_name ilike coalesce((concat('%',:roleName,'%')), role_name) " +
            "and status = coalesce(:isActive, status) ", nativeQuery = true)
    List<RoleEntity> findAllRole(Pageable paging, @Param("roleName") String roleName, @Param("isActive") Integer isActive);
}
