package com.example.LectureManagement.core.entity.Config;

import com.example.LectureManagement.core.entity.mapper.LectureMapper;
import com.example.LectureManagement.core.entity.mapper.SlideMapper;
import com.example.LectureManagement.core.entity.mapper.TeacherMapper;
import org.jdbi.v3.core.Jdbi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class JdbiConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/LectureManagement");
        dataSource.setUsername("postgres");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    public Jdbi jdbi(DataSource dataSource) {
        Jdbi jdbi = Jdbi.create(dataSource);
        jdbi.registerRowMapper(new LectureMapper());
        jdbi.registerRowMapper(new TeacherMapper());
        jdbi.registerRowMapper(new SlideMapper());
        return jdbi;
    }
}