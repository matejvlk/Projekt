package cz.tul.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CitiesDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public List<City> getCities() {

        return jdbc
                .query("select * from cities, states where cities.stateName=states.stateName and states.enabled=true",
                        (ResultSet rs, int rowNum) -> {
                            State state = new State();
                            state.setAuthority(rs.getString("authority"));
                            state.setEnabled(true);
                            state.setStateName(rs.getString("stateName"));

                            City city = new City();
                            city.setId(rs.getInt("id"));
                            city.setCityName(rs.getString("cityName"));
                            city.setState(state);

                            return city;
                        }
                );
    }

    public List<City> getCitites_innerjoin() {

        return jdbc
                .query("select * from cities join states using (stateName) where states.enabled=true",
                        (ResultSet rs, int rowNum) -> {
                            State state = new State();
                            state.setAuthority(rs.getString("authority"));
                            state.setEnabled(true);
                            state.setStateName(rs.getString("stateName"));

                            City city = new City();
                            city.setId(rs.getInt("id"));
                            city.setCityName(rs.getString("cityName"));
                            city.setState(state);

                            return city;
                        }
                );
    }


    public boolean update(City city) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(city);

        return jdbc.update("update cities set cityName=:cityName where id=:id", params) == 1;
    }

    public boolean create(City city) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(city);

        return jdbc.update("insert into cities (stateName, cityName) values (:stateName, :cityName)", params) == 1;
    }

    @Transactional
    public int[] create(List<City> cities) {
        SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(cities.toArray());

        return jdbc.batchUpdate("insert into cities (stateName, cityName) values (:stateName, :cityName)", params);
    }

    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        return jdbc.update("delete from cities where id=:id", params) == 1;
    }

    public City getCity(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from cities, states where cities.stateName=states.stateName and states.enabled=true  and id=:id", params,
                new RowMapper<City>() {

                    public City mapRow(ResultSet rs, int rowNum) throws SQLException {
                        State state = new State();
                        state.setAuthority(rs.getString("authority"));
                        state.setEnabled(true);
                        state.setStateName(rs.getString("stateName"));

                        City city = new City();
                        city.setId(rs.getInt("id"));
                        city.setCityName(rs.getString("cityName"));
                        city.setState(state);

                        return city;
                    }
                });
    }

    public void deleteCities() {
        jdbc.getJdbcOperations().execute("DELETE FROM CITIES");
    }
}
