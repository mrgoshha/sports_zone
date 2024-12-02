package ru.nsu.regSystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SQLRequestService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> createRequest(String request) throws Exception{
        return jdbcTemplate.queryForList(request);
    }
}
