package by.rusak.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;

@Setter
@Getter
@EqualsAndHashCode
@Builder
//@ToString(exclude = {"userName"})
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;

    private String userName;

    private String surname;

    private Timestamp birth;

    private Boolean isDeleted;

    private Timestamp creationDate;

    private Timestamp modificationDate;

    private String login;

    private String password;

    private String driverLicenseNumber;

    private Timestamp driverLicenseDate;

    private String email;

    private Long latitude;

    private Long longitude;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
