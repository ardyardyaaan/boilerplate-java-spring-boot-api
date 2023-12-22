package com.example.boilerplatejavaspringbootapi.restapi.v1.user.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.boilerplatejavaspringbootapi.restapi.v1.user.entity.UserEntity;

/**
 *
 * @author ardyardyaaan
 */

@Repository
public interface UserRepository extends PagingAndSortingRepository<UserEntity, Integer> {

        @Query(value = "select user_id, " +
                        "fullname, " +
                        "email, " +
                        "is_reset_password, " +
                        "token, " +
                        "token_expired, " +
                        "password, " +
                        "status, " +
                        "start_date, " +
                        "end_date, " +
                        "created_by, " +
                        "created_at, " +
                        "updated_by, " +
                        "updated_at from mst_user " +
                        "where user_id = :userId ", nativeQuery = true)
        UserEntity findByUserId(@Param("userId") UserEntity userId);

        @Query(value = "select user_id, " +
                        "fullname, " +
                        "email, " +
                        "is_reset_password, " +
                        "token, " +
                        "token_expired, " +
                        "password, " +
                        "status, " +
                        "start_date, " +
                        "end_date, " +
                        "created_by, " +
                        "created_at, " +
                        "updated_by, " +
                        "updated_at from mst_user " +
                        "where lower(fullname) = :fullname " +
                        "and status = 1 ", nativeQuery = true)
        UserEntity findByFullname(@Param("fullname") String fullname);

        @Query(value = "select user_id, " +
                        "fullname, " +
                        "email, " +
                        "is_reset_password, " +
                        "token, " +
                        "token_expired, " +
                        "password, " +
                        "status, " +
                        "start_date, " +
                        "end_date, " +
                        "created_by, " +
                        "created_at, " +
                        "updated_by, " +
                        "updated_at from mst_user " +
                        "where email = :email " +
                        "and status = 1 ", nativeQuery = true)
        UserEntity findByEmail(@Param("email") String email);

        @Query(value = "select user_id, " +
                        "fullname, " +
                        "email, " +
                        "is_reset_password, " +
                        "token, " +
                        "token_expired, " +
                        "password, " +
                        "status, " +
                        "start_date, " +
                        "end_date, " +
                        "created_by, " +
                        "created_at, " +
                        "updated_by, " +
                        "updated_at from mst_user " +
                        "where cast(:currentDate as date) >= cast(start_date as date) " +
                        "and cast(:currentDate as date) <= cast(end_date as date) " +
                        "and status = 1 order by fullname asc", nativeQuery = true)
        List<UserEntity> findAllUserActive(@Param("currentDate") Date currentDate);

        @Query(value = "select user_id, " +
                        "fullname, " +
                        "email, " +
                        "is_reset_password, " +
                        "token, " +
                        "token_expired, " +
                        "password, " +
                        "status, " +
                        "start_date, " +
                        "end_date, " +
                        "created_by, " +
                        "created_at, " +
                        "updated_by, " +
                        "updated_at from mst_user " +
                        "where token = :token " +
                        "and status = 1 ", nativeQuery = true)
        UserEntity findByToken(@Param("token") String token);

        @Query(value = "select user_id, " +
                        "fullname, " +
                        "email, " +
                        "is_reset_password, " +
                        "token, " +
                        "token_expired, " +
                        "password, " +
                        "status, " +
                        "start_date, " +
                        "end_date, " +
                        "created_by, " +
                        "created_at, " +
                        "updated_by, " +
                        "updated_at from mst_user " +
                        "where user_id in :userId ", nativeQuery = true)
        List<UserEntity> findAllById(@Param("userId") List<UserEntity> userId);

}
