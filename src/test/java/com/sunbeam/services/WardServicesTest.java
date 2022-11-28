package com.sunbeam.services;

import com.sunbeam.daos.IWardDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class WardServicesTest {
    @Mock
    IWardDao wardDao;
    WardServices wardServices;

    @BeforeEach
    void setUp() {
        this.wardServices=new WardServices(this.wardDao);
    }

    @Test
    void getAllWards() {
        wardServices.getAllWards();
        Mockito.verify(wardDao).findAll();
    }
}