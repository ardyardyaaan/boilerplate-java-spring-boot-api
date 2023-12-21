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
        Optional<?> result = Optional.empty();

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
                1,
                new Date());

        roleRepository.save(role);

        List<?> roles = new ArrayList(Arrays.asList(role));
        result = roles.stream().findFirst();

        response.setStatus("success");
        response.setData(result);
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
        response.setMessage("List Role");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> update(ResponseObject response, RoleDto request) {
        Optional<?> result = Optional.empty();
        RoleEntity checkRoleName = roleRepository.findByRoleNameNotId(request.getRole_id(), request.getRole_name().trim().toLowerCase());
        if (checkRoleName != null) {
            response.setMessage("Role Name Already Exist");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        RoleEntity dataRole = roleRepository.findByRoleId(request.getRole_id());
        if (dataRole == null) {
            response.setMessage("Role Not Found");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        dataRole.setRoleName(request.getRole_name());
        dataRole.setStartDate(request.getStart_date());
        dataRole.setEndDate(request.getEnd_date());
        dataRole.setStatus(request.getStatus());
        dataRole.setUpdatedBy(1);
        dataRole.setUpdatedAt(new Date());
        roleRepository.save(dataRole);

        List<?> roles = new ArrayList(Arrays.asList(dataRole));
        result = roles.stream().findFirst();

        response.setStatus("success");
        response.setData(result);
        response.setMessage("Data was Updated Successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public ResponseEntity<?> detail(ResponseObject response, Integer id) {
        Optional<?> result = Optional.empty();

        RoleEntity dataRole = roleRepository.findByRoleId(id);
        if (dataRole == null) {
            response.setMessage("Role Not Found");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }

        List<?> roles = new ArrayList(Arrays.asList(dataRole));
        result = roles.stream().findFirst();

        response.setStatus("success");
        response.setData(result);
        response.setMessage("Detail Role");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
