package by.rusak.domain;

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
@AllArgsConstructor
@NoArgsConstructor
public class CarJdbc {
    private Long id;
    private Integer idType;
    private String plateNumber;
    private Integer productionYear;
    private long rating;
    private String photo;
    private long priceDay;
    private Timestamp creationDate;
    private Timestamp modificationDate;
    private Boolean isDeleted;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }
}
