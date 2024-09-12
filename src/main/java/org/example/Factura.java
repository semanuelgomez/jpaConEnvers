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
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Autoincremental
    private Long id;

    @Column(name = "fecha")  //Se podrian omitir
    private String fecha;

    @Column(name = "total")
    private int total;

    @Column(name = "numero")
    private int numero;



    @ManyToOne(cascade = CascadeType.PERSIST) //Solo queremos persistir un cliente si es que no existe al crear una factura pero si borro una factura no borro al cliente
    @JoinColumn(name="fk_cliente")
    private Cliente cliente;

    //Esto lo comento, ya que si quiero que sea bidireccional, lo tengo que hacer desde detalle factura
    //@OneToMany(cascade = CascadeType.ALL , orphanRemoval = true) //Al ser una composicion, al eliminar una factura, elimino todos sus detalles
    //private List<DetalleFactura> facturas = new ArrayList<DetalleFactura>();

    //Para que sea bidireccional creo el manytoone en detallefactura y luego esto
    @OneToMany (mappedBy = "factura", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default  // @Builder.Default es necesario para inicializar los campos con un valor predeterminado
                      // cuando usamos el patrón Builder, ya que de lo contrario, Lombok no aplica la inicialización
                      // y estos campos quedarían como null, lo que causaría NullPointerException.
    private List<DetalleFactura> detalles = new ArrayList<DetalleFactura>();


}