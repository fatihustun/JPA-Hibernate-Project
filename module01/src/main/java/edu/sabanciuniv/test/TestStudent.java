package edu.sabanciuniv.test;


import edu.sabanciuniv.model.School;
import edu.sabanciuniv.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;

public class TestStudent {

    public static void main(String[] args) {

        //creating student objects

        Student student1 = new Student("Fatih","Ustun","fatih.ustun@sabanciuniv.edu.tr",
                "Information Technology","533357061");
        Student student2 = new Student("Ömer","Ustun","omer.ustun@sabanciuniv.edu.tr",
                "Architecture","5542323522");
        Student student3 = new Student("Salih Fırat","Kara","salih.kara@sabanciuniv.edu.tr",
                "Master of Business Administration","533357061");
        Student student4 = new Student("Emir","Nazik","emirnazik@gmail.com",
                "MBA","5334567183");

        //creating school objects

        School school1 = new School("Sabanci University","Istanbul","Turkey");
        School school2 = new School("Milano Polytechnic university","Milano","Italy");
        School school3 = new School("University of Westminster","London","UK");

        //adding all of them into a list

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);
        studentList.add(student4);

        student1.setSchool(school1);
        student2.setSchool(school2);
        student3.setSchool(school3);

        List<School> schoolList = new ArrayList<>();
        schoolList.add(school1);
        schoolList.add(school2);
        schoolList.add(school3);



        // creating EntityManager to create entity relation from object to db (???)

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
        EntityManager entityManager = emf.createEntityManager();

        //saveSchools(schoolList,entityManager);
        //saveStudents(studentList,entityManager);
        //updateStudentPhoneNumber(entityManager,student4,"0000000000");
        findAllSchools(entityManager);
    }

    private static void saveSchools(List<School> schoolList, EntityManager entityManager){
        //creating for-loop to save all schools in schoolList

        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("School are staring to be added into DB");

        for ( School school: schoolList) {
            System.out.println(school.getName() + " is added into db");
            entityManager.getTransaction().begin(); //begins transactions
            entityManager.persist(school);  //add object into db
            entityManager.getTransaction().commit(); // commits transactions and saved all data into db

        }
        System.out.println("School are added into DB");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");

    }

    private static void saveStudents(List<Student> studentList, EntityManager entityManager) {

        //creating for-loop to save all students in studentList

        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");
        System.out.println("Students are staring to be added into DB");

        for ( Student student: studentList) {
            System.out.println(student.getName() + " is added into db");
            entityManager.getTransaction().begin(); //begins transactions
            entityManager.persist(student);  //add object into db
            entityManager.getTransaction().commit(); // commits transactions and saved all data into db

        }
        System.out.println("Students are added into DB");
        System.out.println("- - - - - - - - - - - - - - - - - - - - - - - - - - - -");

    }

    private static void updateStudentPhoneNumber(EntityManager entityManager, Student student4, String newPhoneNumber) {
        entityManager.getTransaction().begin();

        Student foundStudent = entityManager.createQuery("FROM Student s WHERE s.name = :stdntName", Student.class)
                .setParameter("stdntName", student4.getName())
                .getSingleResult();
        foundStudent.setPhoneNumber(newPhoneNumber);
        entityManager.merge(foundStudent);

        entityManager.getTransaction().commit();
        System.out.println("Student phone number is updated to : " + newPhoneNumber);
    }

    private static void findAllSchools(EntityManager entityManager) {
        TypedQuery schoolJpql = entityManager.createQuery("FROM School s", School.class);
        List<School> schoolList = schoolJpql.getResultList();

        for (School school : schoolList) {
            System.out.println(school);
        }
    }


}
