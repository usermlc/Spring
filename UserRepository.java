package com.example.demo.repositories;

import com.example.demo.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.util.List;

public class UserRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final RowMapper<User> userRowMapper = (ResultSet rs, int rowNum) -> {
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        return user;
    };

    public User findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM users WHERE id = ?", new Object[]{id}, userRowMapper);
    }

    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM users", userRowMapper);
    }

    public void save(User user) {
        jdbcTemplate.update("INSERT INTO users (name, email) VALUES (?, ?)", user.getName(), user.getEmail());
    }

    public void update(User user) {
        jdbcTemplate.update("UPDATE users SET name = ?, email = ? WHERE id = ?", user.getName(), user.getEmail(), user.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM users WHERE id = ?", id);
    }
}
