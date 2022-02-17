package com.spring.SpringbootAllArchetype.domain.dept;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.IntStream;

@ActiveProfiles({"dev","database"})
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestDeptRepository {
    @Autowired
    private DeptRepository deptRepository;

    @Test
    @Order(1)
    @Commit
    public void insert() {
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
                new Dept(
                        i,
                        deptType.get(random.nextInt(deptType.size())),
                        locationType.get(random.nextInt(locationType.size()))
                )
        ));

        //bulk transaction
        deptRepository.saveAll(deptList);


        // Assert
        Assertions.assertThat(deptRepository.findById(10).isPresent()).isTrue();
        Assertions.assertThat(deptRepository.findById(30).isPresent()).isTrue();
        Assertions.assertThat(deptRepository.findById(60).isPresent()).isTrue();
        Assertions.assertThat(deptRepository.findById(99).isPresent()).isTrue();
    }

    @Test
    @Order(2)
    @Commit
    public void update() {
        Integer changeId = 10;
        String changeDeptName = "TESTDEPT";
        String changeLocation = "NEWYORK";

        deptRepository.save(Dept.builder().deptNo(changeId).deptName(changeDeptName).location(changeLocation).build());
        System.out.println("#### DEPT(ID) : " + deptRepository.findById(changeId).get().toString());

        // Assert
        Assertions.assertThat(changeDeptName).isEqualTo(deptRepository.findById(changeId).get().getDeptName());
        Assertions.assertThat(changeLocation).isEqualTo(deptRepository.findById(changeId).get().getLocation());
    }

    @Test
    @Order(3)
    @Commit
    public void delete() {

        // Delete Entity set
        IntStream.range(90,100).forEach(i -> {
            deptRepository.delete(Dept.builder().deptNo(i).build());
                });

        // Assert
        Assertions.assertThat(false).isEqualTo(deptRepository.findById(99).isPresent());
    }
}
