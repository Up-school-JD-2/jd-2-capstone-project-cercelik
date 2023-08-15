package io.upschool.ticketSystem.dto.airplane;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public class AirplaneUpdateRequest {

        private long id;
        private String name;

        private long capacity;

        private long airlineCompanyID;

    }

