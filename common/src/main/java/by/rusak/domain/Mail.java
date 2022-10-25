package by.rusak.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Mail {

    private String from;

    private String to;

    private String subject;

    private String mailContent;
}