package com.sunbeam.services;

import com.sunbeam.daos.IMedicineDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class MedicineServicesTest {
    @Mock
    IMedicineDao medicineDao;
    MedicineServices medicineServices;

    @BeforeEach
    void setUp() {
        this.medicineServices=new MedicineServices(this.medicineDao);
    }

    @Test
    void getAllMedicines() {
        medicineServices.getAllMedicines();
        Mockito.verify(medicineDao).findAll();
    }
}