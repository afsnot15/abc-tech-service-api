package br.com.fiap.abctechservice.application.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class AssistDto {
    
    private Long id;
    private String name;
    private String description;
}
