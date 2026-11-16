package com.mecerintered.springbootprojecttwo.DTO;

public record StudentResponse(
        Long id, String firstName,String lastName, String email
) {
}
