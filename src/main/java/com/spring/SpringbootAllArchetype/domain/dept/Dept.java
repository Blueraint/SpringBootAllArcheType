package com.spring.SpringbootAllArchetype.domain.dept;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
public class Dept {
    @Id
    private Integer deptNo;

    private String deptName;

    private String location;

    // lombok builder type
    @Builder
    public Dept(Integer deptNo, String deptName, String location) {
        this.deptNo = deptNo;
        this.deptName = deptName;
        this.location = location;
    }
}
