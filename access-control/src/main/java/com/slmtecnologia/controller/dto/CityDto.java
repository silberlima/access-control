package com.slmtecnologia.controller.dto;

public record CityDto(Long id,
                      String name,
                      String ibgeCode,
                      StateDto state) {
}
