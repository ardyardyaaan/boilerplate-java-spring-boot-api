package com.example.boilerplatejavaspringbootapi.restapi.v1.userDetail.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.boilerplatejavaspringbootapi.restapi.v1.userDetail.entity.UserDetailEntity;

/**
 *
 * @author ardyardyaaan
 */

@Repository
public interface UserDetailRepository extends PagingAndSortingRepository<UserDetailEntity, Integer> {

    @Query(value = "select user_detail_id, " +
            "user_id, " +
            "role_id, " +
            "status, " +
            "created_by, " +
            "created_at, " +
            "updated_by, " +
            "updated_at from trx_user_detail " +
            "where user_id = :userId " +
            "where role_id = :roleId " +
            "and status = 1 ", nativeQuery = true)
    UserDetailEntity findByUserIdRoleIdActive(@Param("userId") Integer userId, @Param("roleId") Integer roleId);
}
