/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package beleza_pura.com.example.beleza_pura.data;

import beleza_pura.com.example.beleza_pura.entities.Especialidade;
import lombok.Data;

@Data
class EspecialidadeDto {
    private String nome;

    /**
     * 
     */
    public EspecialidadeDto(Especialidade especialidade) {
        this.nome = especialidade.getNome();
    }

}
