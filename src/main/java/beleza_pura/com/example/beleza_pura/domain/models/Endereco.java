package beleza_pura.com.example.beleza_pura.domain.models;



import lombok.Data;

@Data
public class Endereco {
    private String rua;
    private String cidade;
    private String estado;
    private String cep;
    private String pais;
}
