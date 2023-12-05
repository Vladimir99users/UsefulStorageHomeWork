package org.exception;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@Schema(description = "Error UsefulObject")
public class ErrorUsefulObject
{
    private String message;
}
