package com.example.LectureManagement.core.entity.mapper;

import com.example.LectureManagement.core.entity.model.Slide;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SlideMapper implements RowMapper<Slide> {
    @Override
    public Slide map(ResultSet rs, StatementContext ctx) throws SQLException {
        return Slide
                .builder()
                .slideId(UUID.fromString(rs.getString("slide_id")))
                .title(rs.getString("title"))
                .content(rs.getString("content"))
                .orderIndex(rs.getInt("order_index"))
                .build();
    }
}