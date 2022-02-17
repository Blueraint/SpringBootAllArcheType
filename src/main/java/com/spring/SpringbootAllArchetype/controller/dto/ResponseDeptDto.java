package com.spring.SpringbootAllArchetype.controller.dto;

import com.spring.SpringbootAllArchetype.core.dto.MessageDto;
import com.spring.SpringbootAllArchetype.domain.dept.Dept;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * RestController API 와 resource Entity 변환을 위한 Object
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ResponseDeptDto extends MessageDto {
    private Integer deptNo;

    private String deptName;

    private String location;

    public ResponseDeptDto(Dept entity) {
        this.deptNo = entity.getDeptNo();
        this.deptName = entity.getDeptName();
        this.location = entity.getLocation();
    }
}
