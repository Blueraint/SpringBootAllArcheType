package com.spring.SpringbootAllArchetype.controller.api;

import com.spring.SpringbootAllArchetype.controller.dto.RequestDeptDto;
import com.spring.SpringbootAllArchetype.controller.dto.ResponseDeptDto;
import com.spring.SpringbootAllArchetype.core.dto.MessageDto;
import com.spring.SpringbootAllArchetype.service.dept.DeptService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DeptRestController {

    final DeptService deptService;

    @GetMapping(value = "/dept")
    public List<ResponseDeptDto> deptList() {

        return deptService.deptList();
    }

    @GetMapping(value = "/dept/{deptno}")
    public ResponseDeptDto deptDetail(@PathVariable("deptno")int deptno) {

        return deptService.deptDetail(deptno);
    }

    @PostMapping(value = "/dept")
    public ResponseDeptDto deptInsert(RequestDeptDto requestDeptDto) {
        return deptService.deptInsert(requestDeptDto.toEntity());
    }

    @PutMapping(value = "/dept")
    public ResponseDeptDto deptUpdate(RequestDeptDto requestDeptDto) {
        return deptService.deptUpdate(requestDeptDto.toEntity());
    }

    @DeleteMapping(value = "/dept/{deptno}")
    public MessageDto deptDelete(@PathVariable("deptno") int deptno) {
        deptService.deptDelete(deptno);
        return new MessageDto(true, "DELETE");
    }
}
