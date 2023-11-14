package massimomauro.S7L2BCryptAuthorizationCORS.payloads;

import java.util.Date;

public record ErrorsResponseDTO(String message, Date timestamp) {
}
