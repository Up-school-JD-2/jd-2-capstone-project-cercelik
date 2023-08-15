package io.upschool.ticketSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.Errors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotOkResponse {

    private int status;
    @Builder.Default
    private String error = "no message available.";

    public NotOkResponse(int status, Errors errors) {
        this.status = status;
        StringBuilder builder = new StringBuilder();

        for (var err : errors.getAllErrors()) {
            builder.append(err.getDefaultMessage() + "\n");

        }

        error = builder.toString();


    }


}
