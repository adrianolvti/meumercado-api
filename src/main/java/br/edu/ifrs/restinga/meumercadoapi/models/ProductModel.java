package br.edu.ifrs.restinga.meumercadoapi.models;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "products")
public class ProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(columnDefinition = "uuid", length = 36)
    private UUID id;

    @Column(unique = true, nullable = false, length = 3)
    private Integer code;

    @Column(nullable = false, length = 70)
    private String name;

    @Column(nullable = false)
    private double price;

    private Integer inventory;

    @OneToOne
    private TypeModel type;
}
