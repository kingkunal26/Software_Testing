package com.sunbeam.daos;

import com.sunbeam.entities.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class IUserDaoTest {
    @Autowired
    private IUserDao userDao;
    @Test
    void findByEmail() {
        User u=userDao.findByEmail("king@gmail.com");
        assertThat(u).isNotNull();
    }
    @Test
    void existsByEmail() {
        Boolean b=userDao.existsByEmail("king@gmail.com");
        assertThat(b).isTrue();
    }


    @Test
    void findByEmailAndPassword() {
        User u=userDao.findByEmailAndPassword("king@gmail.com","1234");
        assertThat(u).isNotNull();
    }
}