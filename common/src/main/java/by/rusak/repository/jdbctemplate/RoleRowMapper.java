package by.rusak.repository.jdbctemplate;

import by.rusak.domain.Role;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.rusak.repository.columns.RoleTableColumns.CREATION_DATE;
import static by.rusak.repository.columns.RoleTableColumns.ID;
import static by.rusak.repository.columns.RoleTableColumns.MODIFICATION_DATE;
import static by.rusak.repository.columns.RoleTableColumns.ROLE_NAME;


@Component
public class RoleRowMapper implements RowMapper<Role> {

    private static final Logger log = Logger.getLogger(RoleRowMapper.class);

    @Override
    public Role mapRow(ResultSet rs, int i) throws SQLException {
        log.info("Role row mapping start");

        Role role = new Role();

        role.setId(rs.getLong(ID));
        role.setRoleName(rs.getString(ROLE_NAME));
        role.setCreationDate(rs.getTimestamp(CREATION_DATE));
        role.setModificationDate(rs.getTimestamp(MODIFICATION_DATE));

        log.info("Role row mapping end");
        return role;
    }
}
