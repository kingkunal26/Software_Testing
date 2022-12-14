package com.sunbeam.services;
import java.util.List;


import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sunbeam.daos.IDoctorDao;
import com.sunbeam.daos.IDoctorVisitDao;
import com.sunbeam.daos.IEmployeeDao;
import com.sunbeam.daos.IPatientDao;
import com.sunbeam.daos.IUserDao;
import com.sunbeam.daos.IWardDao;
import com.sunbeam.dtos.ChargesCalculationBeanPatient;
import com.sunbeam.dtos.MedicineAssignedDataBackinBean;
import com.sunbeam.dtos.PatientDataBacking;
import com.sunbeam.entities.Doctor;
import com.sunbeam.entities.DoctorVisit;
import com.sunbeam.entities.MedicineAssigned;
import com.sunbeam.entities.Patient;
import com.sunbeam.entities.User;
import com.sunbeam.entities.Ward;

import static com.sunbeam.dtos.PatientDataBacking.*;
import static com.sunbeam.dtos.MedicineAssignedDataBackinBean.*;
import static com.sunbeam.dtos.ChargesCalculationBeanPatient.*;
@NoArgsConstructor
@Service @Transactional
public class PatientServices {
	@Autowired
	IUserDao userDao;
	@Autowired
	IEmployeeDao employeeDao;
	@Autowired
	IPatientDao patientDao;
	@Autowired
	IDoctorDao doctorDao;
	@Autowired
	IDoctorVisitDao visitsDao;
	
	@Autowired
	IWardDao wardDao;

	public PatientServices(IPatientDao patientDao) {
		this.patientDao = patientDao;
	}


	public int addPatient(PatientDataBacking patientDetails) {
		userDao.insertIntoUsers(0, patientDetails.getFirstName(), patientDetails.getLastName(), patientDetails.getEmail(), patientDetails.getPassword(), patientDetails.getCellNo(), patientDetails.getRole(), patientDetails.getSecurityQuestion(), patientDetails.getSecurityAnswer());
		User user=userDao.findByEmail(patientDetails.getEmail());
		int updateCount=patientDao.insertIntoPatients(0, user.getId(),patientDetails.getDoctorId() , patientDetails.getWardId(), patientDetails.getDateOfAdmission(), patientDetails.getBloodGroup(), patientDetails.getDob(), patientDetails.getPrescription(), patientDetails.getBedAlloted(), patientDetails.getPaymentStatus(), patientDetails.getPatientProblem());
		Patient patient=patientDao.findByUserId(user.getId());
		visitsDao.insertIntoDoctorVisitsTable(0, patient.getId(), patientDetails.getDoctorId(),0);
		
		return updateCount;
	}
	
	public List<PatientDataBacking> getAllPatients(){
		List<Patient> patients=patientDao.findAll();
		List<PatientDataBacking> patientList=createPatient(patients);
		return patientList;
		
	}
	
	public PatientDataBacking getPatientById(int id) {
		Patient patient=patientDao.getById(id);
		PatientDataBacking patientDetailsToSend=getByIdPatient(patient);
		return patientDetailsToSend;
		
				
	}
	//get patients medicines by patient id
	public List<MedicineAssignedDataBackinBean> getMedicineByPatId(int id) {
		Patient patient=patientDao.getById(id);
		List<MedicineAssigned> medicines=patient.getMedicines();
		List<MedicineAssignedDataBackinBean> medicineToSend=createMedicineListForPatient(medicines);
		return medicineToSend;
		
				
	}
	
	//update patient details
	public void updatePatientDetails(PatientDataBacking patientDetails) {
		Patient updatePatient = patientDao.getById(patientDetails.getPatId());
		//to add to visit table
		
			DoctorVisit initVisit=visitsDao.getVisitsByPatIdAndDoctorId(patientDetails.getPatId(), patientDetails.getDoctorId());
			System.out.println("------------------>initvisit"+initVisit);
			if(initVisit==null) {
				visitsDao.insertIntoDoctorVisitsTable(0, patientDetails.getPatId(), patientDetails.getDoctorId(), 0);
			}
		Ward updateWard=wardDao.getById(patientDetails.getWardId());//got corrusponding ward by ward Id
		updateWard.addPatient(updatePatient);//added patient in ward list
		updatePatient.setWard(updateWard);//new ward set in patient
		//======================================================================
		
		Doctor updatedDoctor=doctorDao.getById(patientDetails.getDoctorId());//got new doctor by id 
		
		
		
		updatedDoctor.addPatient(updatePatient);//patient added in doctor list 
		updatePatient.setDoctor(updatedDoctor);//doctor added to patient list
		
		updatePatient.getUser().setFirstName(patientDetails.getFirstName());
		updatePatient.getUser().setLastName(patientDetails.getLastName());
		updatePatient.getUser().setCellNo(patientDetails.getCellNo());
		
		updatePatient.setDob(patientDetails.getDob());
		updatePatient.setBedAlloted(patientDetails.getBedAlloted());
		updatePatient.setBloodGroup(patientDetails.getBloodGroup());
		//=======================================
		//update visits

		
		
		
		Patient savedPatient = patientDao.save(updatePatient);
	
		
		
	}
	public int removePatientById(int id) {
		patientDao.deleteById(id);
		return 1;
	}
	
	public ChargesCalculationBeanPatient calculateChargesByPatId(int patId) {
		ChargesCalculationBeanPatient patientCharges=new ChargesCalculationBeanPatient();
		int daysStayed = patientDao.calculateDaysOfStayOfPatient(patId);
		Patient patient=patientDao.getById(patId);
		patientCharges=calculateCharges(patient, daysStayed);
		return patientCharges;
	}
	
	public void updatePaymentStatusByPatId(PatientDataBacking patientData) {
		Patient patient=patientDao.getById(patientData.getPatId());
		patient.setPaymentStatus(patientData.getPaymentStatus());
		patientDao.save(patient);
	}
	//check if bedalloted exits
	public Boolean checkIfBedAvailable(PatientDataBacking bedData) {
		
		return patientDao.existsByBedAllotedAndWardId(bedData.getBedAlloted(),bedData.getWardId()); 
	}

	public List<PatientDataBacking> getPatientsOfDocter(int id) {
		List<Patient> patients=patientDao.findAll();
		List<PatientDataBacking> patientList=createPatientsOfDoctor(patients,id);
		return patientList;
		
	}
	
	
	
}
