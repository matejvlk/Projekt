package cz.tul.data;

/**
 * Created by akasa on 20.1.2015.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class OffersDao {

    @Autowired
    private NamedParameterJdbcOperations jdbc;

    public List<Offer> getOffers() {

        return jdbc
                .query("select * from offers, users where offers.username=users.username and users.enabled=true",
                        (ResultSet rs, int rowNum) -> {
                            User user = new User();
                            user.setAuthority(rs.getString("authority"));
                            user.setEmail(rs.getString("email"));
                            user.setEnabled(true);
                            user.setName(rs.getString("name"));
                            user.setUsername(rs.getString("username"));

                            Offer offer = new Offer();
                            offer.setId(rs.getInt("id"));
                            offer.setText(rs.getString("text"));
                            offer.setUser(user);

                            return offer;
                        }
                );
    }

    public List<Offer> getOffers_innerjoin() {

        return jdbc
                .query("select * from offers join users using (username) where users.enabled=true",
                        (ResultSet rs, int rowNum) -> {
                            User user = new User();
                            user.setAuthority(rs.getString("authority"));
                            user.setEmail(rs.getString("email"));
                            user.setEnabled(true);
                            user.setName(rs.getString("name"));
                            user.setUsername(rs.getString("username"));

                            Offer offer = new Offer();
                            offer.setId(rs.getInt("id"));
                            offer.setText(rs.getString("text"));
                            offer.setUser(user);

                            return offer;
                        }
                );
    }


    public boolean update(Offer offer) {
        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                offer);

        return jdbc.update("update offers set text=:text where id=:id", params) == 1;
    }

    public boolean create(Offer offer) {

        BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(
                offer);

        return jdbc
                .update("insert into offers (username, text) values (:username, :text)",
                        params) == 1;
    }

    @Transactional
    public int[] create(List<Offer> offers) {

        SqlParameterSource[] params = SqlParameterSourceUtils
                .createBatch(offers.toArray());

        return jdbc
                .batchUpdate("insert into offers (username, text) values (:username, :text)", params);
    }

    public boolean delete(int id) {
        MapSqlParameterSource params = new MapSqlParameterSource("id", id);

        return jdbc.update("delete from offers where id=:id", params) == 1;
    }

    public Offer getOffer(int id) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id", id);

        return jdbc.queryForObject("select * from offers, users where offers.username=users.username and users.enabled=true  and id=:id", params,
                new RowMapper<Offer>() {

                    public Offer mapRow(ResultSet rs, int rowNum)
                            throws SQLException {
                        User user = new User();
                        user.setAuthority(rs.getString("authority"));
                        user.setEmail(rs.getString("email"));
                        user.setEnabled(true);
                        user.setName(rs.getString("name"));
                        user.setUsername(rs.getString("username"));

                        Offer offer = new Offer();
                        offer.setId(rs.getInt("id"));
                        offer.setText(rs.getString("text"));
                        offer.setUser(user);

                        return offer;
                    }

                });
    }


    public void deleteOffers() {
        jdbc.getJdbcOperations().execute("DELETE FROM OFFERS");
    }


}
