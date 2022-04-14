package br.com.fiap.abctechservice.application.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Negative;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderLocationDto {
    private Double latitude;
    private Double longitude;

    @PastOrPresent
    private Date dateTime;

}
