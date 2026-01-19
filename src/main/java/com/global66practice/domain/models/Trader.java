package com.global66practice.domain.models;

import java.util.UUID;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Trader {
    private UUID id;
    private String name;
    private String specie;
    private Pocket pocket;
}
