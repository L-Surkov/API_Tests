package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class RegisterUserResponse {
    private String id;
    private String token;
    @JsonProperty("_meta")
    private Meta meta;
}