package com.spring.SpringbootAllArchetype.service.dept;

import com.spring.SpringbootAllArchetype.controller.dto.ResponseDeptDto;
import com.spring.SpringbootAllArchetype.core.exception.MyServiceException;
import com.spring.SpringbootAllArchetype.domain.dept.Dept;
import com.spring.SpringbootAllArchetype.domain.dept.DeptRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeptServiceImpl implements DeptService {

    final DeptRepository deptRepository;

    @Override
    public List<ResponseDeptDto> deptList() throws MyServiceException {
        List<ResponseDeptDto> responseDeptDtoList = new ArrayList<>();

        deptRepository.findAll().forEach(dept -> {
            log.debug("### DEPT : " + dept.toString());
            responseDeptDtoList.add(new ResponseDeptDto(dept));
        });

        return responseDeptDtoList;
    }

    @Override
    public ResponseDeptDto deptDetail(Integer deptNo) throws MyServiceException {

        Optional<Dept> optionalDept = deptRepository.findById(deptNo);
        Dept dept = optionalDept.orElseThrow(() -> new MyServiceException("Dept not found(param : deptNo)"));

        return new ResponseDeptDto(dept);
    }

    // insert : hibernate save
    @Override
    @Transactional
    public ResponseDeptDto deptInsert(Dept dept) throws MyServiceException {
        Optional<Dept> optionalDept = deptRepository.findById(dept.getDeptNo());
        if(!optionalDept.isPresent()) deptRepository.save(dept);
        else throw new MyServiceException("deptNo already exists");

        return new ResponseDeptDto(dept);
    }

    // update : hibernate save
    @Override
    @Transactional
    public ResponseDeptDto deptUpdate(Dept dept) throws MyServiceException {
        Optional<Dept> optionalDept = deptRepository.findById(dept.getDeptNo());
        if(optionalDept.isPresent()) return new ResponseDeptDto(deptRepository.save(dept));
        else throw new MyServiceException("deptNo Not Found");
    }

    @Override
    @Transactional
    public void deptDelete(Integer deptNo) throws MyServiceException {
        deptRepository.deleteById(deptNo);
    }
}
