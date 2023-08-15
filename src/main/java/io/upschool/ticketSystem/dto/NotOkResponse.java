package io.upschool.ticketSystem.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotOkResponse {

    private int status;

    @Builder.Default
    private String error = "no message available.";


}
