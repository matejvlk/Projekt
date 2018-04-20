package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
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

        params.addValue("stateName", state.getStateName());
        params.addValue("enabled", state.isEnabled());
        params.addValue("authority", state.getAuthority());

        return jdbc.update("insert into states (stateName, enabled, authority) values (:stateName, :enabled, :authority)", params) == 1;
    }

    public boolean exists(String stateName) {
        return jdbc.queryForObject("select count(*) from states where stateName=:stateName",
                new MapSqlParameterSource("name", stateName), Integer.class) > 0;
    }

    public List<State> getAllStates() {
        return jdbc.query("select * from states", BeanPropertyRowMapper.newInstance(State.class));
    }

    public void deleteStates() {
        jdbc.getJdbcOperations().execute("DELETE FROM CITIES");
        jdbc.getJdbcOperations().execute("DELETE FROM STATES");
    }
}
