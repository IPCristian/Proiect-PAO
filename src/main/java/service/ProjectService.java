package service;

import domain.*;
import exceptions.InvalidDataException;
import persistence.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ProjectService {

    private final DoctorRepository doctorRepository = new DoctorRepository();
    private final PatientRepository patientRepository = new PatientRepository();
    private final InsuranceRepository insuranceRepository = new InsuranceRepository();
    private final OfficeRepository officeRepository = new OfficeRepository();
    private final AppointmentRepository appointmentRepository = new AppointmentRepository();
    private final EquipmentRepository equipmentRepository = new EquipmentRepository();
    private final MedicineRepository medicineRepository = new MedicineRepository();
    private final ReadFileService readService = ReadFileService.getInstance();
    private final WriteFileService writeService = WriteFileService.getInstance();
    private int doctorCount = 0;
    private int patientCount = 0;
    private int officeCount = 0;

    public void exit() throws IOException {
        List<String[]> output = new ArrayList<>();
        Doctor[] doctors = getAllDoctors();
        for (Doctor doctor : doctors)
        {
            String[] doctorData = {doctor.getLastName() + doctor.getFirstName(),doctor.getEmail(),
                    String.valueOf(doctor.getAge()),String.valueOf(doctor.getYearsOfExperience()),
                    doctor.getSpecialization()};
            // System.out.println(Arrays.toString(doctorData));
            output.add(doctorData);
        }
        writeService.writeLines("src/main/java/data/doctors.csv",output);
        output.clear();
        Patient[] patients = getAllPatients();
        for (Patient patient : patients)
        {
            int expireYear = insuranceRepository.get(patient.getId()).getYear_expire();
            String[] patientData = {patient.getLastName() + patient.getFirstName(),patient.getEmail(),
                    String.valueOf(patient.getAge()),String.valueOf(patient.getDoctor_id()),
                    patient.getDiagnosis(),String.valueOf(expireYear)};
            output.add(patientData);
        }
        writeService.writeLines("src/main/java/data/patients.csv",output);
        output.clear();
        Medical_Office[] offices = getAllOffices();
        for (Medical_Office office : offices)
        {
            String[] officeData = {String.valueOf(office.getFloor()), String.valueOf(office.getDoor_number())};
            output.add(officeData);
        }
        writeService.writeLines("src/main/java/data/offices.csv",output);
        output.clear();
        Appointment[] appointments = getAllAppointments();
        for (Appointment appoint : appointments)
        {
            String[] appointData = {String.valueOf(appoint.getHour()),String.valueOf(appoint.getMinute()),
                    String.valueOf(appoint.getOffice_id()),String.valueOf(appoint.getPatient_id()),
                    String.valueOf(appoint.getDoctor_id())};
            output.add(appointData);
        }
        writeService.writeLines("src/main/java/data/appointments.csv",output);

        System.exit(0);
    }

    public void initializeData() throws IOException {
        List<String[]> inputs = readService.readLines("src/main/java/data/doctors.csv");
        for (String[] doctor : inputs)
        {
            String[] name = doctor[0].split(" ");
            String lastName = name[0];
            StringBuilder firstName = new StringBuilder();
            for (int i=1; i<name.length;i++)
            {
                firstName.append(" ").append(name[i]);
            }
            String email = doctor[1];
            String ageString = doctor[2];
            String yearsOfExperienceString = doctor[3];
            String specialization = doctor[4];

            if (ageString.matches("^\\d+$"))
            {
                try {
                    registerNewDoctor(lastName,firstName.toString(),email,Integer.parseInt(ageString),
                            Integer.parseInt(yearsOfExperienceString),specialization);
                } catch (InvalidDataException invalidData)
                {
                    System.out.println(invalidData.getMessage());
                }
            }
        }
        inputs = readService.readLines("src/main/java/data/patients.csv");
        for (String[] patient : inputs)
        {
            String[] name = patient[0].split(" ");
            String lastName = name[0];
            StringBuilder firstName = new StringBuilder();
            for (int i=1; i<name.length;i++)
            {
                firstName.append(" ").append(name[i]);
            }
            String email = patient[1];
            String ageString = patient[2];
            String doctorId = patient[3];
            String diagnosis = patient[4];
            String expireYear = patient[5];
            try {
                registerNewPatient(lastName, String.valueOf(firstName),email,Integer.parseInt(ageString),
                        Integer.parseInt(doctorId),diagnosis,expireYear);
            } catch (InvalidDataException invalidData) {
                System.out.println(invalidData.getMessage());
            }
        }
        inputs = readService.readLines("src/main/java/data/offices.csv");
        for (String[] office : inputs)
        {
            String floor = office[0];
            String doorNr = office[1];
            try {
                registerNewOffice(Integer.parseInt(floor),Integer.parseInt(doorNr));
            } catch (InvalidDataException invalidData) {
                System.out.println(invalidData.getMessage());
            }
        }
        inputs = readService.readLines("src/main/java/data/appointments.csv");
        for (String[] appoint : inputs)
        {
            String hour = appoint[0];
            String minute = appoint[1];
            String officeId = appoint[2];
            String patientId = appoint[3];
            String doctorId = appoint[4];

            try {
                registerNewAppoint(Integer.parseInt(doctorId),Integer.parseInt(patientId),
                        Integer.parseInt(officeId),Integer.parseInt(hour),Integer.parseInt(minute));
            } catch (InvalidDataException invalidData) {
                System.out.println(invalidData.getMessage());
            }
        }
    }

    public void registerNewDoctor(String lastName, String firstName, String email, int age,
                                  int yearsOfExperience, String specialization)
            throws InvalidDataException
    {
        if (lastName == null || lastName.trim().isEmpty())
        {
            throw new InvalidDataException("Invalid last name");
        }
        if (firstName == null || firstName.trim().isEmpty())
        {
            throw new InvalidDataException("Invalid first name");
        }
        if (email == null || email.trim().isEmpty())
        {
            throw new InvalidDataException("Invalid email");
        }
        if (age < 18)
        {
            throw new InvalidDataException("Invalid age");
        }
        if (yearsOfExperience < 4)          // You need to have at least 4 years of experience to work as a doctor
        {
            throw new InvalidDataException("Invalid count at numbers of experience");
        }
        if (specialization == null || specialization.trim().isEmpty())
        {
            throw new InvalidDataException("Invalid specialization");
        }

        int id = ++doctorCount;
        Doctor doctor = new Doctor(id, lastName,firstName,email,age,yearsOfExperience,specialization);
        doctorRepository.add(doctor);
    }

    public void removeOffice(int id) throws InvalidDataException {
        if (!officeRepository.getID(id))
        {
            throw new InvalidDataException("\n> Invalid Office ID");
        }

        officeRepository.delete(id);
    }

    public void registerNewMedicine(String name,String dosage,String sideEffects) throws InvalidDataException {
        if (name == null || name.trim().isEmpty())
        {
            throw new InvalidDataException("Invalid Medicine name");
        }
        if (dosage == null || dosage.trim().isEmpty())
        {
            throw new InvalidDataException("Invalid recommended dosage");
        }
        if (sideEffects == null || sideEffects.trim().isEmpty())
        {
            throw new InvalidDataException("Invalid side effects");
        }

        Medicine med = new Medicine(name,dosage,sideEffects);
        medicineRepository.add(med);
    }

    public void registerNewAppoint(int doctor_id,int patient_id, int office_id,int hour,int minute) throws InvalidDataException
    {
        if (!doctorRepository.getID(doctor_id))
        {
            throw new InvalidDataException("Invalid Doctor ID");
        }
        if (!patientRepository.getID(patient_id))
        {
            throw new InvalidDataException("Invalid Patient ID");
        }
        if (!officeRepository.getID(office_id))
        {
            throw new InvalidDataException("Invalid Office ID");
        }
        if (hour < 0 || hour > 24)
        {
            throw new InvalidDataException("Invalid Appointment hour");
        }
        if (minute < 0 || minute > 60)
        {
            throw new InvalidDataException("Invalid Appointment minutes");
        }

        Appointment appointment = new Appointment(doctor_id,patient_id,office_id,hour,minute);
        appointmentRepository.add(appointment);
    }

    public void registerNewEquipment(int office_id,int weight) throws InvalidDataException
    {
        if (!officeRepository.getID(office_id))
        {
            throw new InvalidDataException("Invalid Office ID");
        }

        Equipment eq = new Equipment(office_id,weight);
        equipmentRepository.add(eq);
    }

    public void registerNewOffice(int floor,int door_number) throws InvalidDataException
    {
        if (floor < 0 || floor > 3)                             // Floors start from 0 to 3.
        {
            throw new InvalidDataException("Invalid floor");
        }
        if (door_number > 20)                                   // Maximum of 20 rooms per floor.
        {
            throw new InvalidDataException("Invalid door number");
        }

        int id = ++officeCount;
        Medical_Office office = new Medical_Office(id,floor,door_number);
        officeRepository.add(office);
    }

    public void registerNewPatient(String lastName, String firstName, String email, int age, int doctor_id,
                                   String diagnosis, String expireYear) throws InvalidDataException
    {
        if (lastName == null || lastName.trim().isEmpty())
        {
            throw new InvalidDataException("Invalid last name");
        }
        if (firstName == null || firstName.trim().isEmpty())
        {
            throw new InvalidDataException("Invalid first name");
        }
        if (email == null || email.trim().isEmpty())
        {
            throw new InvalidDataException("Invalid email");
        }
        if (age < 18)
        {
            throw new InvalidDataException("Invalid age");
        }
        if (!doctorRepository.getID(doctor_id))
        {
            throw new InvalidDataException("Invalid Doctor ID");
        }

        int id = ++patientCount;
        Insurance insurance = new Insurance(id,Integer.parseInt(expireYear));
        Patient patient = new Patient(id, lastName,firstName,email,age,doctor_id,diagnosis,insurance);
        patientRepository.add(patient);
        insuranceRepository.add(insurance);
    }

    public void removePatient(int id) throws InvalidDataException
    {
        if (!patientRepository.getID(id)) {
            throw new InvalidDataException("\n> Invalid Patient ID");
        }

        patientRepository.delete(id);
    }

    public void removeDoctor(int id) throws InvalidDataException
    {
        if (!doctorRepository.getID(id))
        {
            throw new InvalidDataException("\n> Invalid Doctor ID");
        }

        doctorRepository.delete(id);
    }

    public void updatePatient(int id, String diagnosis) throws InvalidDataException
    {
        if (!patientRepository.getID(id)) {
            throw new InvalidDataException("\n> Invalid Patient ID");
        }

        patientRepository.update_diagnosis(id,diagnosis);
    }

    public void updateDoctor(int id,String specialization) throws InvalidDataException
    {
        if (!doctorRepository.getID(id))
        {
            throw new InvalidDataException("\n> Invalid Doctor ID");
        }

        doctorRepository.update_spec(id,specialization);
    }

    public Doctor[] getAllDoctors()
    {
        List<Doctor> result = new ArrayList<>();
        for (int i=0; i<doctorRepository.getSize();i++)
        {
            if (doctorRepository.get(i) != null) {
                result.add(doctorRepository.get(i));
            }
        }

        return result.toArray(new Doctor[0]);
    }

    public Patient[] getAllPatients()
    {
        List<Patient> result = new ArrayList<>();
        for (int i = 0; i< patientRepository.getSize(); i++)
        {
            if (patientRepository.get(i) != null)
            {
                result.add(patientRepository.get(i));
            }
        }

        return result.toArray(new Patient[0]);
    }

    public Insurance[] getAllInsurances()
    {
        List<Insurance> result = new ArrayList<>();
        for (int i=0;i<patientRepository.getSize();i++)
        {
            if (patientRepository.get(i) != null)
            {
                if (insuranceRepository.get(patientRepository.get(i).getId()) != null)
                {
                    result.add(insuranceRepository.get(patientRepository.get(i).getId()));
                }
            }
        }

        return result.toArray(new Insurance[0]);
    }

    public Equipment[] getAllEquips(int id)
    {
        List<Equipment> result = new ArrayList<>();
        for (int i=0;i<equipmentRepository.getSize();i++)
        {
            if (equipmentRepository.get(i) != null)
            {
                if (equipmentRepository.get(i).getId_office() == id)
                {
                    result.add(equipmentRepository.get(i));
                }
            }
        }

        return result.toArray(new Equipment[0]);
    }

    public Medical_Office[] getAllOffices()
    {
        List<Medical_Office> result = new ArrayList<>();
        for (int i = 0; i< officeRepository.getSize(); i++)
        {
            if (officeRepository.get(i) != null)
            {
                result.add(officeRepository.get(i));
            }
        }

        return result.toArray(new Medical_Office[0]);
    }

    public Appointment[] getAllAppointmentsFromDoctor(int id)
    {
        List<Appointment> result = new ArrayList<>();
        for (int i=0; i < appointmentRepository.getSize();i++)
        {
            if (appointmentRepository.get(i) != null)
            {
                if (appointmentRepository.get(i).getDoctor_id() == id)
                {
                    result.add(appointmentRepository.get(i));
                }
            }
        }

        return result.toArray(new Appointment[0]);
    }
    public Appointment[] getAllAppointments()
    {
        List<Appointment> result = new ArrayList<>();
        for (int i=0; i < appointmentRepository.getSize();i++)
        {
            if (appointmentRepository.get(i) != null)
            {
                result.add(appointmentRepository.get(i));
            }
        }

        return result.toArray(new Appointment[0]);
    }
}
