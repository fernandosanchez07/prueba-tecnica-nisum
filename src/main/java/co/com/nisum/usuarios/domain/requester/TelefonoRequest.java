package co.com.nisum.usuarios.domain.requester;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TelefonoRequest {

    private String number;
    private String cityCode;
    private String countryCode;
}
