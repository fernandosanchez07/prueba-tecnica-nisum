package co.com.nisum.usuarios.domain.requester;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoRequest {

    private Integer number;
    private Integer cityCode;
    private Integer countryCode;
}
