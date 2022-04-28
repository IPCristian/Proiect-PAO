# Proiect-PAO

## Entities:

>* Person
>* Patient
>* Doctor
>* Medical Office
>* Appointment
>* Equipment
>* Insurance
>* Medicine

# Description

###The program simulates a Hospital. It keeps track of every Doctor, Patient, Equipment, etc. Each patient has associated to him a medical insurance that, upon his deletion, also gets removed. When a doctor is erased, all of his patients also get removed. An appointment requires a valid doctor, patient and office for it to be created. A piece of equipment is attached to an office. All the entities have certain requirements to their attributes that need to be passed before an object can be added to their respective collection.

# To Do List:

## Stage 1

- [x]  minimum of 10 queries on at least 8 types of objects
- [x]  include simple classes with private/protected attributes and methods
- [x]  include at least 2 different collections capable of administering the objects in the application
- [x]  use inheritance for some of the classes used within the collections
- [x]  at least one service class that exposes the system's operations
- [x]  a main class that calls the service's methods

## Stage 2

- [x]  Extend the project from the 1st stage by persisting the data using files:
>* CSV files will be used to store at least 4 types of objects from the first stage. Each column in the file is separated with a comma. Example: name,surname,age
>* Generic singleton services will be created for reading and writing from/to files
>* At system startup, the data will be automatically loaded from the files.
- [x]  An auditing service will need to be created that will log to a CSV file each time an action from the first stage is performed. Structure of the file: name_of_action,timestamp.

## Not implemented yet :
* When a Doctor is removed, his patients are split amongst the other doctors. If there isn't any, then they are deleted.
* When an Office is removed, all the equipment from inside is split amongst the other offices.
* A query to show all the equipment information for an Office.
* A way to link Medicine to Patient Diagnosis and/or Appointment.