package com.example.LectureManagement.core.entity.mapper;

import com.example.LectureManagement.core.entity.model.Lecture;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class LectureMapper implements RowMapper<Lecture> {
    @Override
    public Lecture map(ResultSet rs, StatementContext ctx) throws SQLException {
        return Lecture
                .builder()
                .lectureId(UUID.fromString(rs.getString("lecture_id")))
                .title(rs.getString("title"))
                .description(rs.getString("description"))
                .durationMinutes(Integer.parseInt(rs.getString("duration_minutes")))
                .location(rs.getString("location"))
                .build();
    }
}
