package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class StatesDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    @Transactional
    public boolean create(State state) {

        MapSqlParameterSource params = new MapSqlParameterSource();

        params.addValue("name", state.getName());
        params.addValue("enabled", state.isEnabled());
        params.addValue("authority", state.getAuthority());

        return jdbc.update("insert into users (username, name, password, email, enabled, authority) values (:username, :name, :password, :email, :enabled, :authority)", params) == 1;
    }

    public boolean exists(String username) {
        return jdbc.queryForObject("select count(*) from users where username=:username",
                new MapSqlParameterSource("username", username), Integer.class) > 0;
    }

    public List<User> getAllUsers() {
        return jdbc.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
    }

    public void deleteUsers() {
        jdbc.getJdbcOperations().execute("DELETE FROM OFFERS");
        jdbc.getJdbcOperations().execute("DELETE FROM USERS");
    }
}
