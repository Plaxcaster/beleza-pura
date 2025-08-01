package beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class HorarioFuncionamentoEmbeddable {
    private String abertura;  // Opening time (e.g. "08:00")
    private String fechamento; // Closing time (e.g. "20:00")

    /**
     * Checks if the establishment is currently open based on current time
     * @return true if open, false otherwise
     */
    public boolean isOpenNow() {
        // Implementation would compare current time with abertura/fechamento
        // This is a placeholder for actual time comparison logic
        return true;
    }

    /**
     * Gets formatted operating hours
     * @return String in format "08:00 - 20:00"
     */
    public String getFormattedHours() {
        return String.format("%s - %s", abertura, fechamento);
    }

    /**
     * Validates the opening hours format
     * @return true if valid, false otherwise
     */
    public boolean isValid() {
        // Basic validation - could be enhanced with proper time parsing
        return abertura != null && fechamento != null &&
                abertura.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$") &&
                fechamento.matches("^([0-1]?[0-9]|2[0-3]):[0-5][0-9]$");
    }
}