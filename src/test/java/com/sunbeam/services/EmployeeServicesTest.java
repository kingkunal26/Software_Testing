package com.sunbeam.services;

import com.sunbeam.daos.IEmployeeDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class EmployeeServicesTest {
    @Mock
    IEmployeeDao employeeDao;

    EmployeeServices employeeServices;

    @BeforeEach
    void setUp() {
        this.employeeServices=new EmployeeServices(this.employeeDao);
    }

    @Test
    void getAllEmployees() {
        employeeServices.getAllEmployees();
        Mockito.verify(employeeDao).findAll();
    }
}