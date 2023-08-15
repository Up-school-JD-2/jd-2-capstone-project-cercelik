package io.upschool.ticketSystem.dto.airplane;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class AirplaneUpdateRequest {

        private long id;


        @NotBlank
        @NotEmpty
        @NotNull
        @Size(min = 2, max = 100,message = "Name alanı minimum 2 maksimum 100 karater olabilir.")
        private String name;


        @Range(min = 2, max = 100,message = "capacity alanı minimum 2 maksimum 100 olabilir.")
        private long capacity;

        private long airlineCompanyID;

    }

