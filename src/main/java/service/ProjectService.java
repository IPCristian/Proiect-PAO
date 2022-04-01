package service;

import domain.Doctor;
import domain.Pacient;
import exceptions.InvalidDataException;
import persistence.DoctorRepository;
import persistence.PacientRepository;

import java.util.ArrayList;
import java.util.List;

public class ProjectService {

    private final DoctorRepository doctorRepository = new DoctorRepository();
    private final PacientRepository pacientRepository = new PacientRepository();
    private int doctorCount = 0;
    private int pacientCount = 0;

    public void registerNewDoctor(String lastName, String firstName, String email, int age, int yearsOfExperience, String specialization)
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

    public void registerNewPacient(String lastName,String firstName, String email, int age, int doctor_id,
                                   String diagnosis) throws InvalidDataException
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
        if (doctorRepository.get(doctor_id-1) == null)
        {
            throw new InvalidDataException("Invalid Doctor ID");
        }

        int id = ++pacientCount;
        Pacient pacient = new Pacient(id, lastName,firstName,email,age,doctor_id,diagnosis);
        pacientRepository.add(pacient);
    }

    public void removePacient(int id) throws InvalidDataException
    {
        if (!pacientRepository.getID(id)) {
            throw new InvalidDataException("\n> Invalid Pacient ID");
        }

        pacientRepository.delete(id);
    }

    public void updatePacient(int id) throws InvalidDataException
    {
        if (!pacientRepository.getID(id)) {
            throw new InvalidDataException("\n> Invalid Pacient ID");
        }


        Pacient tempPacient;
        pacientRepository.update();
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

    public Pacient[] getAllPacients()
    {
        List<Pacient> result = new ArrayList<>();
        for (int i=0; i<pacientRepository.getSize();i++)
        {
            if (pacientRepository.get(i) != null)
            {
                result.add(pacientRepository.get(i));
            }
        }

        return result.toArray(new Pacient[0]);
    }

}
