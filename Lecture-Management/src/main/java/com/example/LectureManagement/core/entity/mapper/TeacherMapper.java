package com.example.LectureManagement.core.entity.mapper;

import com.example.LectureManagement.core.entity.model.Teacher;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TeacherMapper implements RowMapper<Teacher> {
    @Override
    public Teacher map(ResultSet rs, StatementContext ctx) throws SQLException {
        return Teacher
                .builder()
                .teacherId(UUID.fromString(rs.getString("teacher_id")))
                .name(rs.getString("name"))
                .salary(Integer.valueOf(rs.getString("salary")))
                .email(rs.getString("email"))
                .phoneNumber(rs.getString("phone_number"))
                .build();
    }
}
