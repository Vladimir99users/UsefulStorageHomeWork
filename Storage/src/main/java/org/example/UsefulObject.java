package org.example;


import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsefulObject
{
    private long id;
    private String name;
    private String description;
    private String link;

}
