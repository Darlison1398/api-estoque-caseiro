package com.example.cadastro.teste.model.estoque;

import com.example.cadastro.teste.model.agent.User_model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estoque")
@Getter
@Setter
@NoArgsConstructor
public class Estoque_model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String descricao;

    @Column
    private int quantidade;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User_model user;


    /*
     * Com o relacionamento muitos-para-um (@ManyToOne), você está 
     * definindo que muitos registros da entidade Estoque_model podem 
     * estar associados a um único registro da entidade User_model. 
     */
    
}
