package com.sunbeam.daos;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IEmployeeDaoTest {
    @Autowired
    IEmployeeDao employeeDao;
    @Test
    void getEmpIdByEmail() {
        int e=employeeDao.getEmpIdByEmail("king@gmail.com");
        Assertions.assertThat(e).isEqualTo(113);
    }
}