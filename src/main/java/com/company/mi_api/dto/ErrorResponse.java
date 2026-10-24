package com.company.mi_api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private String codigo;           // "USUARIO_NO_ENCONTRADO"
    private String mensaje;          // Mensaje detallado
    private LocalDateTime timestamp; // Cuándo ocurrió
    private int status;              // Código HTTP (404, 400, etc.)
}