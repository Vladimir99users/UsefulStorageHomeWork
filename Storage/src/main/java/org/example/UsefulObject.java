package org.example;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class UsefulObject
{
    private long id;
    private String name;
    private String description;
    private String url;

}
