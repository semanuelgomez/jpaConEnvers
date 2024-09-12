package org.example;

import lombok.*;
import org.hibernate.envers.Audited;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Builder



@Entity //Esto se debe guardar en la bd
@Table
@Audited
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Autoincremental
    private Long id;

    @Column(name = "nombre")  //Se podrian omitir
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "dni",unique = true) //el unique es para que no se puedan repetir
    private int dni;

    //Relaciones

    @OneToOne(cascade = CascadeType.ALL) //tipo de cascadeo, como se propagan los cambios de padres a hijos
    @JoinColumn(name="fk_domicilio")  //se crea una columna que tenga la fk
    private Domicilio domicilio;      //la relacion inversa se realiza con mappedby

    @OneToMany(mappedBy = "cliente")
    private List<Factura> facturas = new ArrayList<Factura>();


}
