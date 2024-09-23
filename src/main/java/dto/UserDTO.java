package dto;

import lombok.*;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor


public class UserDTO {

    private String email;
    private String password;
}
