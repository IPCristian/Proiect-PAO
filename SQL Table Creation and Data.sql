-------------------------------------------------------------------------------------------------------------------------------
create table DOCTOR (
    id int NOT NULL,
    lastName varchar(255),
    firstName varchar(255),
    email varchar(255),
    age int,
    yearsOfExperience int,
    specialization varchar(255),
    PRIMARY KEY (id)
);

insert into doctor
values (1,'John','Doe','John.234@gmail.com',47,12,'Psychology');
insert into doctor
values (2,'Agata','Loren Willis','WilLor@yahoo.com',37,7,'Dermatology');
insert into doctor
values (3,'Emelie','Dudley','DudDud_Eme@yahoo.com',41,10,'Neurology');
insert into doctor
values (4,'Ibrahim','Don Valdino','IbraKo@rocketmail.com',64,21,'Anesthesiology');

select * from doctor;
-------------------------------------------------------------------------------------------------------------------------------
create table PATIENT (
    id int NOT NULL,
    lastName varchar(255),
    firstName varchar(255),
    email varchar(255),
    age int,
    doctor_id int not null,
    diagnosis varchar(255),
    insuranceYear int,
    PRIMARY KEY (id),
    FOREIGN KEY (doctor_id) references DOCTOR(id)
    );
    
insert into patient
values (1,'Gene','A Griffith','armand_boeh9@gmail.com',40,1,'Broken rib cage',2026);
insert into patient
values (2,'John','Bannister','delpha2009@hotmail.com',43,2,'Diabetes',2023);    
insert into patient
values (3,'Hector','Warren','hector.warren@yahoo.com',36,4,'Flu',2026);

select * from patient;

delete from patient where id = 4;
-------------------------------------------------------------------------------------------------------------------------------
create table OFFICE (
    id int NOT NULL,
    floor int,
    door_number int,
    PRIMARY KEY (id)
    );
    
insert into office
values (1,1,1);
insert into office
values (2,1,2);
insert into office
values (3,1,3);
insert into office
values (4,2,1);
insert into office
values (5,2,2);
insert into office
values (6,2,3);

select * from office;
-------------------------------------------------------------------------------------------------------------------------------
create table APPOINTMENT (
    doctor_id int not null,
    patient_id int not null,
    office_id int not null,
    hour int,
    minute int,
    foreign key (doctor_id) references doctor(id),
    foreign key (patient_id) references patient(id),
    foreign key (office_id) references office(id)
    );
    
insert into appointment
values (3,2,1,18,30);
insert into appointment
values (3,2,3,16,45);

select * from appointment;
-------------------------------------------------------------------------------------------------------------------------------