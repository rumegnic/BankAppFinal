 package ro.bank.api.generic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GenericResponse {

    private Integer status;
    private String message;

    public static GenericResponse from(HttpStatus httpStatus, String message) {
        return new GenericResponse(httpStatus.value(), message);
    }

}
