package beleza_pura.com.example.beleza_pura.repositories.jpa.jpaEntities;

import beleza_pura.com.example.beleza_pura.entities.Cliente;
import beleza_pura.com.example.beleza_pura.entities.Especialidade;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "CLIENTE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteTableEntity {
    @Id
    private UUID id;
    private String nome;
    private String email;
    private String telefone;

    public ClienteTableEntity(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.telefone = cliente.getTelefone();
    }

    public Cliente toClienteEntidade() {
        return Cliente.builder()
                .id(id)
                .nome(nome)
                .email(email)
                .telefone(telefone)
                .build();
    }
}