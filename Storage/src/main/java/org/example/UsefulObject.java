package org.example;


import lombok.*;
import org.jetbrains.annotations.NotNull;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UsefulObject
{

    private long id;
    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private String link;

}
