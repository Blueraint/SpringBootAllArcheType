package com.spring.SpringbootAllArchetype.service.dept;

import com.spring.SpringbootAllArchetype.controller.dto.ResponseDeptDto;
import com.spring.SpringbootAllArchetype.core.exception.MyServiceException;
import com.spring.SpringbootAllArchetype.domain.dept.Dept;

import java.util.List;

public interface DeptService {

    public List<ResponseDeptDto> deptList() throws MyServiceException;

    public ResponseDeptDto deptDetail(Integer deptNo) throws MyServiceException;

    public ResponseDeptDto deptInsert(Dept dept) throws MyServiceException;

    public ResponseDeptDto deptUpdate(Dept dept) throws MyServiceException;

    public void deptDelete(Integer deptNo) throws MyServiceException;

}
