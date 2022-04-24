package br.com.fiap.abctechservice.application.dto;

import java.util.Date;

import lombok.*;

import javax.validation.constraints.Negative;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class OrderLocationDto {
    private Double latitude;
    private Double longitude;

    @PastOrPresent
    private Date dateTime;

}
