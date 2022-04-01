package view;

import domain.*;
import exceptions.InvalidDataException;
import service.ProjectService;

import java.util.Scanner;

public class ConsoleApp {

    private final Scanner s = new Scanner(System.in);
    private final ProjectService service = new ProjectService();

    public static void main(String[] args)
    {
        ConsoleApp app = new ConsoleApp();
        //noinspection InfiniteLoopStatement
        while (true)
        {
            app.showMenu();
            int option = app.readOption();
            app.execute(option);
        }
    }

    private void showMenu()   // To Do : Add menus for separate entities for a cleaner client experience.
    {
        System.out.println
                ("""
                         \n _____________________________________________________________________________________\s
                         |                Main Menu                ||          (1) Show all doctors           |
                         |-----------------------------------------||-----------------------------------------|
                         |           (2) Add new doctor            ||     (3) Change Doctor Specialization    |
                         |-----------------------------------------||-----------------------------------------|
                         |            (4) Remove Doctor            ||          (5) Show all patients          |
                         |-----------------------------------------||-----------------------------------------|
                         |           (6) Add new patient           ||      (7) Change Patient Diagnosis       |
                         |-----------------------------------------||-----------------------------------------|
                         |           (8) Remove patient            ||        (9) Show all insurances          |
                         |-----------------------------------------||-----------------------------------------|
                         |          (10) Show all offices          ||             (11) Add Office             |
                         |-----------------------------------------||-----------------------------------------|
                         |           (12) Remove Office            ||    (13) View Doctor's appointments      |
                         |-----------------------------------------||-----------------------------------------|
                         |          (14) Add Appointment           ||           (15) Add Equipment            |
                         |-----------------------------------------||-----------------------------------------|
                         |            (16) Add Medicine            ||                (17) Exit                |
                         |_________________________________________||_________________________________________|
                        """);

        System.out.print("\n> Option: ");
    }

    private int readOption()
    {
        try {
            int option = readInt();
            if (option >= 1 && option <= 17)
            {
                return option;
            }
        } catch (InvalidDataException ignored) {}

        System.out.println("> Invalid option.");
        return readOption();
    }

    private void execute(int option)
    {
        switch (option) {
            case 1 -> getDoctors();
            case 2 -> addDoctor();
            case 3 -> updateDoctor();
            case 4 -> deleteDoctor();
            case 5 -> getPatients();
            case 6 -> addPatient();
            case 7 -> updatePatient();
            case 8 -> deletePatient();
            case 9 -> getInsurances();
            case 10 -> getOffices();
            case 11 -> addOffice();
            case 12 -> deleteOffice();
            case 13 -> viewDoctorAppoint();
            case 14 -> addAppointment();
            case 15 -> addEquipment();
            case 16 -> addMedicine();
            case 17 -> System.exit(0);
        }
    }

    private int readInt() throws InvalidDataException {
        String line = s.nextLine();
        if (line.matches("^\\d+$"))
        {
            return Integer.parseInt(line);
        }
        else
        {
            throw new InvalidDataException("> Invalid number");
        }
    }

    private void addEquipment()
    {
        System.out.print("\n> ID Office: ");
        String office_id = s.nextLine();
        System.out.print("\n> Weight (KG): ");
        String weight = s.nextLine();

        try {
            service.registerNewEquipment(Integer.parseInt(office_id),Integer.parseInt(weight));
        } catch (InvalidDataException invalidData) {
            System.out.println(invalidData.getMessage());
        }
    }

    private void viewDoctorAppoint()
    {
        System.out.print("\n> ID Doctor: ");
        String doctor_id = s.nextLine();
        for (Appointment appointment : service.getAllAppointmentsFromDoctor(Integer.parseInt(doctor_id)))
        {
            System.out.println("\n> Patient ID: " + appointment.getPatient_id() + " | Office ID: " +
                    appointment.getOffice_id() + " | Time: " + appointment.getHour() + ":" +
                    appointment.getMinute());
        }
    }

    private void addMedicine()
    {
        System.out.print("\n> Medicine name: ");
        String name = s.nextLine();
        System.out.print("\n> Recommended dosage: ");
        String dosage = s.nextLine();
        System.out.print("\n> Side effects of usage: ");
        String sideEffects = s.nextLine();

        try {
            service.registerNewMedicine(name,dosage,sideEffects);
        } catch (InvalidDataException invalidData)
        {
            System.out.println(invalidData.getMessage());
        }
    }

    private void addAppointment()
    {
        System.out.print("\n> ID Doctor: ");
        String doctor_id = s.nextLine();
        System.out.print("\n> ID Patient: ");
        String patient_id = s.nextLine();
        System.out.print("\n> ID Office: ");
        String office_id = s.nextLine();
        System.out.print("\n> Hour: ");
        String hour = s.nextLine();
        System.out.print("\n> Minute: ");
        String minute = s.nextLine();

        try {
            service.registerNewAppoint(Integer.parseInt(doctor_id),Integer.parseInt(patient_id),
                    Integer.parseInt(office_id),Integer.parseInt(hour),Integer.parseInt(minute));
        } catch (InvalidDataException invalidData)
        {
            System.out.println(invalidData.getMessage());
        }
    }

    private void getInsurances()
    {
        System.out.println("\n> Insurances:");
        for (Insurance i : service.getAllInsurances())
        {
            System.out.print("\n> Patient ID: " + i.getId_person() + " | Expiration Year: " + i.getYear_expire());
        }

    }

    private void deletePatient()
    {
        System.out.print("\n> ID of the Patient: ");
        String id_patient = s.nextLine();
        try {
            service.removePatient(Integer.parseInt(id_patient));
        } catch (InvalidDataException invalidData)
        {
            System.out.println(invalidData.getMessage());
        }
    }

    private void deleteOffice()
    {
        System.out.print("\n> ID of the Office: ");
        String office_id = s.nextLine();
        try {
            service.removeOffice(Integer.parseInt(office_id));
        } catch (InvalidDataException invalidData)
        {
            System.out.println(invalidData.getMessage());
        }
    }

    private void deleteDoctor()
    {
        System.out.print("\n> ID of the Doctor: ");
        String id_doctor = s.nextLine();
        try {
            service.removeDoctor(Integer.parseInt(id_doctor)); // If we remove a doctor, we have to remove all of
            for (Patient patient : service.getAllPatients())   // his patients.
            {
                if (patient.getDoctor_id() == Integer.parseInt(id_doctor))
                {
                    try {
                        service.removePatient(patient.getId());
                    }
                    catch (InvalidDataException invalidData)
                    {
                        System.out.println(invalidData.getMessage());
                    }
                }
            }
        } catch (InvalidDataException invalidData)
        {
            System.out.println(invalidData.getMessage());
        }
    }

    private void updatePatient()
    {
        System.out.print("\n> ID of the Patient: ");
        String id_patient = s.nextLine();

        System.out.print("\n> New Diagnosis: ");
        String diagnosis = s.nextLine();
        try {
            service.updatePatient(Integer.parseInt(id_patient),diagnosis);
        } catch (InvalidDataException invalidData)
        {
            System.out.println(invalidData.getMessage());
        }
    }

    private void updateDoctor()
    {
        System.out.print("\n> ID of the Doctor: ");
        String id_doctor = s.nextLine();
        System.out.print("\n> New Specialization: ");
        String specialization = s.nextLine();

        try {
            service.updateDoctor(Integer.parseInt(id_doctor), specialization);
        } catch (InvalidDataException invalidData)
        {
            System.out.println(invalidData.getMessage());
        }
    }

    private void getDoctors()
    {
        System.out.println("\n> Doctors: \n");
        for (Doctor d : service.getAllDoctors())
        {
            System.out.println("\n> ID: " + d.getId() + " | Last Name: " + d.getLastName() + " | First Name: " +
                    d.getFirstName() + " | Age: " + d.getAge() +  " | Email: " + d.getEmail() +
                    " | Years of Experience: " + d.getYearsOfExperience() + " | Specialization: "
                    + d.getSpecialization());
        }
    }

    private void getPatients()
    {
        System.out.println("\n> Patients: \n");
        for (Patient d : service.getAllPatients())
        {
            System.out.println("\n> ID: " + d.getId() + " | Last Name: " + d.getLastName() + " | First Name: " +
                    d.getFirstName() + " | Age: " + d.getAge() +
                    " | Email: " + d.getEmail() + " | Doctor's ID: " + d.getDoctor_id() + " | Diagnosis: "
                    + d.getDiagnosis());
        }
    }

    private void getOffices()
    {
        System.out.println("\n> Offices: \n");
        for (Medical_Office m : service.getAllOffices())
        {
            System.out.println("\n> ID: " + m.getId_office() + " | Floor: " + m.getFloor() +
                    " | Door number: " + m.getDoor_number());
        }
    }

    private void addDoctor()
    {
        System.out.print("> Name: ");
        String[] name = s.nextLine().split(" ");
        String lastName = name[0];
        StringBuilder firstName = new StringBuilder();
        for (int i=1; i<name.length;i++)
        {
            firstName.append(" ").append(name[i]);
        }
        System.out.print("> Email: ");
        String email = s.nextLine();
        System.out.print("> Age: ");
        String ageString = s.nextLine();
        System.out.print("> Years of experience: ");
        String yearsOfExperienceString = s.nextLine();
        System.out.print("> Specialization: ");
        String specialization = s.nextLine();

        if (ageString.matches("^\\d+$"))
        {
            try {
                service.registerNewDoctor(lastName,firstName.toString(),email,Integer.parseInt(ageString),
                        Integer.parseInt(yearsOfExperienceString),specialization);
            } catch (InvalidDataException invalidData)
            {
                System.out.println(invalidData.getMessage());
            }
        }
        else
        {
            System.out.println("> Invalid age");
        }
    }

    private void addPatient()
    {
        System.out.print("> Name: ");
        String[] name = s.nextLine().split(" ");
        String lastName = name[0];
        StringBuilder firstName = new StringBuilder();
        for (int i=1; i<name.length;i++)
        {
            firstName.append(" ").append(name[i]);
        }
        System.out.print("> Email: ");
        String email = s.nextLine();
        System.out.print("> Age: ");
        String ageString = s.nextLine();
        System.out.print("> Doctor's ID: ");
        String doctors_id = s.nextLine();
        System.out.print("> Diagnosis: ");
        String diagnosis = s.nextLine();
        System.out.print("> Insurance Expire year: ");
        String expireYear = s.nextLine();

        if (ageString.matches("^\\d+$"))
        {
            try {
                service.registerNewPatient(lastName,firstName.toString(),email,Integer.parseInt(ageString),
                        Integer.parseInt(doctors_id),diagnosis,expireYear);
            } catch (InvalidDataException invalidData)
            {
                System.out.println(invalidData.getMessage());
            }
        }
        else
        {
            System.out.println("> Invalid age");
        }
    }

    private void addOffice()
    {
        System.out.print("> Floor: ");
        String floor = s.nextLine();
        System.out.print("> Door number: ");
        String door_number = s.nextLine();

        try {
            service.registerNewOffice(Integer.parseInt(floor),Integer.parseInt(door_number));
        } catch (InvalidDataException invalidData)
        {
            System.out.println(invalidData.getMessage());
        }
    }

}
