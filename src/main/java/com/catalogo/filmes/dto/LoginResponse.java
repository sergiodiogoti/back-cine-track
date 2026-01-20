package com.catalogo.filmes.dto;

import java.util.List;

public record LoginResponse(String token, List<String> roles) {}
