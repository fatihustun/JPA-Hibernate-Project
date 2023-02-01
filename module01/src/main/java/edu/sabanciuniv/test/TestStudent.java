package edu.sabanciuniv.test;


import edu.sabanciuniv.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

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


        //adding all of them into a list

        List<Student> studentList = new ArrayList<>();
        studentList.add(student1);
        studentList.add(student2);
        studentList.add(student3);


        // creating EntityManager to create entity relation from object to db (???)

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("mysqlPU");
        EntityManager entityManager = emf.createEntityManager();

        saveStudents(studentList,entityManager);
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
}
