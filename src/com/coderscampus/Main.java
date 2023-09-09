package com.coderscampus;

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

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String line;
            br.readLine(); // Skip the header line
            while ((line = br.readLine()) != null) {
                String[] studentData = line.split(",");
                studentDataList.add(studentData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Sort all students by grade
        Collections.sort(studentDataList, new GradeComparator());

        // Display all student names
        for (String[] studentData : studentDataList) {
            String studentName = studentData[1].trim(); // Assuming student name is in the second column
            System.out.println(studentName);
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
