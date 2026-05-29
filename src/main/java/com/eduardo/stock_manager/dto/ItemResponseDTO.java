package com.eduardo.stock_manager.dto;

import java.time.LocalDate;

public record ItemResponseDTO(Long id, String nome, String marca, LocalDate dataValidade, Integer quantidade, Integer estoqueMinimo, boolean isAtivo) {}
