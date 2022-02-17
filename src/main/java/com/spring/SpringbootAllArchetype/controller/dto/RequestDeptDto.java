package com.spring.SpringbootAllArchetype.controller.dto;

import com.spring.SpringbootAllArchetype.domain.dept.Dept;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class RequestDeptDto {
    private Integer deptNo;

    private String deptName;

    private String location;

    // lombok builder type
    @Builder
    public RequestDeptDto(Integer deptNo, String deptName, String location) {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.location = location;
    }

    /* Dto <-> entity transfer method */
    public Dept toEntity() {
        return Dept.builder().
                deptNo(deptNo).
                deptName(deptName).
                location(location).build();
    }
}
