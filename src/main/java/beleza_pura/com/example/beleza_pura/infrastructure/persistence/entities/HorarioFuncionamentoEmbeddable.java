package beleza_pura.com.example.beleza_pura.infrastructure.persistence.entities;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyEnumerated;
import lombok.Getter;
import lombok.Setter;
import java.time.DayOfWeek;
import java.util.Map;

/**
 * Represents business hours for an establishment
 * Maps opening hours for each day of the week
 *
 * Example:
 * {
 *   MONDAY: "09:00-18:00",
 *   TUESDAY: "09:00-18:00",
 *   WEDNESDAY: "09:00-20:00"
 * }
 */
@Embeddable
@Getter
@Setter
public class HorarioFuncionamentoEmbeddable {

    @ElementCollection
    @CollectionTable(
            name = "estabelecimento_horarios",
            joinColumns = @JoinColumn(name = "estabelecimento_id")
    )
    @MapKeyEnumerated(EnumType.STRING)
    @Column(name = "horario", length = 20)
    private Map<DayOfWeek, String> horarios;

    /**
     * Helper method to check if establishment is open on a given day
     */
    public boolean isOpenOn(DayOfWeek day) {
        return horarios != null && horarios.containsKey(day);
    }

    /**
     * Gets opening time for a specific day
     * @return Opening time string (e.g. "09:00") or null if closed
     */
    public String getOpeningTime(DayOfWeek day) {
        if (horarios == null || !horarios.containsKey(day)) {
            return null;
        }
        return horarios.get(day).split("-")[0];
    }

    /**
     * Gets closing time for a specific day
     * @return Closing time string (e.g. "18:00") or null if closed
     */
    public String getClosingTime(DayOfWeek day) {
        if (horarios == null || !horarios.containsKey(day)) {
            return null;
        }
        String[] times = horarios.get(day).split("-");
        return times.length > 1 ? times[1] : null;
    }
}