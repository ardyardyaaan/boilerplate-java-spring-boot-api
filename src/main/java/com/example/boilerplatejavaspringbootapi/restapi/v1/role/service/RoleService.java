package com.example.boilerplatejavaspringbootapi.restapi.v1.role.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.boilerplatejavaspringbootapi.helper.ResponseList;
import com.example.boilerplatejavaspringbootapi.helper.ResponseObject;
import com.example.boilerplatejavaspringbootapi.request.RequestListDto;
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.dto.RoleDto;
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.entity.RoleEntity;
import com.example.boilerplatejavaspringbootapi.restapi.v1.role.repository.RoleRepository;

/**
 *
 * @author ardyardyaaan
 */

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public ResponseEntity<?> create(ResponseObject response, RoleDto request) {

        String roleName = request.getRole_name().trim().toLowerCase();
        RoleEntity checkRoleName = roleRepository.findByRoleName(roleName);

        if (checkRoleName != null) {
            response.setMessage("Role Name Already Exist");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        RoleEntity role = new RoleEntity(
                roleName,
                request.getStart_date(),
                request.getEnd_date(),
                1,
                0,
                new Date());

        roleRepository.save(role);

        response.setStatus("success");
        response.setMessage("Data was Saved Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> list(ResponseList response, RequestListDto request) {
        
        if (request.getPage() <= 0) {
            response.setMessage("Jumlah Data dalam 1 halaman tidak boleh kurang dari 0");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        List<Optional<?>> result = new ArrayList<>();
        Pageable paging = PageRequest.of(request.getPage() - 1,request.getPer_page());

        List<RoleEntity> roles = roleRepository.findAllRole(paging, request.getKeyword(), request.getIs_active());

        roles.forEach((data) -> {
            List<?> listData = new ArrayList(Arrays.asList(data));
            Optional<?> datas = listData.stream().findFirst();
            result.add(datas);
        });

        response.setStatus("success");
        response.setData(result);
        response.setMessage("List Roles");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
