package br.com.fiap.abctechservice.application.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderLocationDto {

    private Double latitude;
    private Double longitude;
    private Date dateTime;

}
