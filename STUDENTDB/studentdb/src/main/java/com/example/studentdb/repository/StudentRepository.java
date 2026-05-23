package com.example.studentdb.repository;

import com.example.studentdb.db.DatabaseConfig;
import com.example.studentdb.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {

    public List<Student> findAll() throws SQLException {
        String sql = "SELECT id, name, course, year_level FROM students ORDER BY id";

        List<Student> students = new ArrayList<>();

        try (
                Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getString("year_level")
                ));
            }
        }

        return students;
    }

    public void save(String name, String course, String yearLevel) throws SQLException {
        String sql = "INSERT INTO students(name, course, year_level) VALUES (?, ?, ?)";

        try (
                Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, name);
            stmt.setString(2, course);
            stmt.setString(3, yearLevel);
            stmt.executeUpdate();
        }
    }

    public void update(int id, String name, String course, String yearLevel) throws SQLException {
        String sql = "UPDATE students SET name = ?, course = ?, year_level = ? WHERE id = ?";

        try (
                Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setString(1, name);
            stmt.setString(2, course);
            stmt.setString(3, yearLevel);
            stmt.setInt(4, id);
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM students WHERE id = ?";

        try (
                Connection conn = DatabaseConfig.getConnection();
                PreparedStatement stmt = conn.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}