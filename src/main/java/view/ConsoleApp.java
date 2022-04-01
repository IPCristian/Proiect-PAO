package view;

import domain.Doctor;
import domain.Pacient;
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

    private void showMenu()
    {
        System.out.println
                ("""
                         \n  _______________________________\s
                         |           Main Menu           |
                         |-------------------------------|
                         |      (1) Show all doctors     |
                         |-------------------------------|
                         |       (2) Add new doctor      |
                         |-------------------------------|
                         |      (3) Show all pacients    |
                         |-------------------------------|
                         |       (4) Add new pacient     |
                         |-------------------------------|
                         |       (5) Remove pacient      |
                         |-------------------------------|
                         |           (6) Exit            |
                         |_______________________________|
                        """);

        System.out.print("\n> Option: ");
    }

    private int readOption()
    {
        try {
            int option = readInt();
            if (option >= 1 && option <= 6)
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
            case 3 -> getPacients();
            case 4 -> addPacient();
            case 5 -> deletePacient();
            case 6 -> System.exit(0);
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

    private void deletePacient()
    {
        System.out.print("\n> ID of the Pacient: ");
        String id_pacient = s.nextLine();
        try {
            service.removePacient(Integer.parseInt(id_pacient));
        } catch (InvalidDataException invalidData)
        {
            System.out.println(invalidData.getMessage());
        }
    }

    private void updatePacient()
    {
        System.out.println("\n> ID of the Pacient: ");
        String id_pacient = s.nextLine();

    }

    private void getDoctors()
    {
        System.out.println("\n> Doctors: \n");
        for (Doctor d : service.getAllDoctors())
        {
            System.out.println("\n> ID: " + d.getId() + " | Last Name: " + d.getLastName() + " | First Name: " + d.getFirstName() + " | Age: " + d.getAge() +
                    " | Email: " + d.getEmail() + " | Years of Experience: " + d.getYearsOfExperience() + " | Specialization: " + d.getSpecialization());
        }
    }

    private void getPacients()
    {
        System.out.println("\n> Pacients: \n");
        for (Pacient d : service.getAllPacients())
        {
            System.out.println("\n> ID: " + d.getId() + " | Last Name: " + d.getLastName() + " | First Name: " + d.getFirstName() + " | Age: " + d.getAge() +
                    " | Email: " + d.getEmail() + " | Doctor's ID: " + d.getDoctor_id() + " | Diagnosis: " + d.getDiagnosis());
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
                service.registerNewDoctor(lastName,firstName.toString(),email,Integer.parseInt(ageString),Integer.parseInt(yearsOfExperienceString),specialization);
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

    private void addPacient()
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

        if (ageString.matches("^\\d+$"))
        {
            try {
                service.registerNewPacient(lastName,firstName.toString(),email,Integer.parseInt(ageString),Integer.parseInt(doctors_id),diagnosis);
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


}
