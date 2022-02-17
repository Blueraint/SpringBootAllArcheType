package com.spring.SpringbootAllArchetype.service.dept;

import com.spring.SpringbootAllArchetype.controller.dto.ResponseDeptDto;
import com.spring.SpringbootAllArchetype.domain.dept.Dept;
import com.spring.SpringbootAllArchetype.domain.dept.DeptRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

@ActiveProfiles({"dev","database"})
@SpringBootTest
@AutoConfigureMockMvc // webMvc Test configuration annotation
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDeptService {

    @Autowired
    DeptService deptService;

    @Autowired
    DeptRepository deptRepository;

    @Test
    @Order(1)
    @Commit
    @Transactional
    public void deptInsertService() {
        ArrayList<String> deptType = new ArrayList<String>(){{
            add("IT"); add("ACCOUNT"); add("HR"); add("FACTORY MAINTAIN"); add("CSR");
        }};
        ArrayList<String> locationType = new ArrayList<String>(){{
            add("SEOUL"); add("BUSAN"); add("DAEJEON"); add("INCHEON"); add("DAEGU");
        }};
        ArrayList<Dept> deptList = new ArrayList<>();
        Random random = new Random();

        // Create Random Entity set
        IntStream.range(1,100).forEach(i -> deptList.add(
                Dept.builder().deptNo(i)
                        .deptName(deptType.get(random.nextInt(deptType.size())))
                        .location(locationType.get(random.nextInt(locationType.size())))
                        .build()
                )
        );
        // insert transaction
        deptList.forEach(dept -> deptService.deptInsert(dept));

        deptList.forEach(dept -> {
            ResponseDeptDto responseDeptDto = deptService.deptDetail(dept.getDeptNo());
            Assertions.assertThat(responseDeptDto.getDeptNo()).isEqualTo(dept.getDeptNo());
        });
    }

    @Test
    @Order(2)
    @Commit
    @Transactional
    public void deptUpdateService() {
        ArrayList<String> deptType = new ArrayList<String>(){{
            add("IT"); add("ACCOUNT"); add("HR"); add("FACTORY MAINTAIN"); add("CSR");
        }};
        ArrayList<String> locationType = new ArrayList<String>(){{
            add("SEOUL"); add("BUSAN"); add("DAEJEON"); add("INCHEON"); add("DAEGU");
        }};
        ArrayList<Dept> deptList = new ArrayList<>();
        Random random = new Random();

        String changeDeptName = "TESTDEPT";
        String changeLocationName = "TESTLOCATION";

        // Update special location set
        deptService.deptUpdate(Dept.builder().deptNo(10).
                deptName(changeDeptName).
                location(changeLocationName).build());

        ResponseDeptDto responseDeptDto = deptService.deptDetail(10);

        System.out.println(responseDeptDto.toString());

        Assertions.assertThat(changeDeptName).isEqualTo(responseDeptDto.getDeptName());
        Assertions.assertThat(changeLocationName).isEqualTo(responseDeptDto.getLocation());
    }

    @Test
    @Order(3)
    @Commit
    @Transactional
    public void deptDeleteService() {
        // delete special location set
        deptService.deptDelete(99);

        boolean isPresent = deptRepository.findById(99).isPresent();
        System.out.println(isPresent);

        Assertions.assertThat(false).isEqualTo(isPresent);
    }
}
