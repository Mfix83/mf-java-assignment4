package com.coderscampus; 

import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main { 
    public static void main(String[] args) {
        String csvFile = "data/StudentList.csv"; 
        List<String[]> studentDataList = new ArrayList<>();
        List<String[]> course1Students = new ArrayList<>();
        List<String[]> course2Students = new ArrayList<>();
        List<String[]> course3Students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] studentData = line.split(",");
                studentDataList.add(studentData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String[] studentData : studentDataList) {
            String course = studentData[2].trim(); 
            if (course.equals("COMPSCI 321")) {
                course1Students.add(studentData);
            } else if (course.equals("STAT 239")) {
                course2Students.add(studentData);
            } else if (course.equals("COMPSCI 312")) {
                course3Students.add(studentData);
            }
        }

        Collections.sort(course1Students, new GradeComparator());
        Collections.sort(course2Students, new GradeComparator());
        Collections.sort(course3Students, new GradeComparator());

        writeStudentsToCSV(course1Students, "course1.csv");
        writeStudentsToCSV(course2Students, "course2.csv");
        writeStudentsToCSV(course3Students, "course3.csv");
    }

    private static void writeStudentsToCSV(List<String[]> students, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write("Student ID,Student Name,Course,Grade\n");
            for (String[] studentData : students) {
                String studentInfo = String.join(",", studentData);
                writer.write(studentInfo + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static class GradeComparator implements Comparator<String[]> {
        @Override
        public int compare(String[] student1, String[] student2) {
            int grade1 = Integer.parseInt(student1[3].trim()); 
            int grade2 = Integer.parseInt(student2[3].trim());
            return Integer.compare(grade2, grade1); 
        }
    }
}
