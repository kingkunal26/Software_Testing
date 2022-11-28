package com.sunbeam.services;

import com.sunbeam.daos.IUserDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServicesTest {
    @Mock
    IUserDao userDao;

    UserServices userServices;

    @BeforeEach
    void setUp() {
        this.userServices=new UserServices(userDao);
    }

    @Test
    void findAllUsers() {
        userServices.findAllUsers();
        Mockito.verify(userDao).findAll();
    }


}