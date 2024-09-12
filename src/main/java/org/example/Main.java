package org.example;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.hibernate.boot.model.process.spi.MetadataBuildingProcess.build;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example-unit");

        EntityManager entityManager = entityManagerFactory.createEntityManager();  //revisar siempre si uso la instancia que empieza con minuscula
        System.out.println("Inicio");


        try {
            entityManager.getTransaction().begin(); //Inicia la transaccion

            Domicilio domicilio1 = Domicilio.builder()
                    .nombrecalle("San Juan")
                    .numero(2030)
                    .build();

            Cliente cliente1 = Cliente.builder()
                    .nombre("Luciano")
                    .apellido("Oro")
                    .dni(26373993)
                    .domicilio(domicilio1)
                    .build();

            domicilio1.setCliente(cliente1);



            /*Domicilio dom=entityManager.find(Domicilio.class, 1L);
            Cliente cliente =entityManager.find(Cliente.class,1L);

            System.out.println("Cliente de domicilio" + dom.getCliente().getId());
            System.out.println("domicilio de cliente" + cliente.getDomicilio().getNombre_calle());*/

            Factura factura1 = Factura.builder()
                    .fecha("10/08/2024")
                    .numero(1)
                    .cliente(cliente1)
                    .build();

            Categoria perecederos = Categoria.builder()
                    .denominacion("perecederos")
                    .build();

            Categoria lacteos = Categoria.builder()
                    .denominacion("lacteos")
                    .build();

            Categoria limpieza = Categoria.builder()
                    .denominacion("limpieza")
                    .build();

            Articulo art1 = Articulo.builder()
                    .cantidad(200)
                    .denominacion("Yogurt")
                    .precio(20)
                    .build();

            Articulo art2 = Articulo.builder()
                    .cantidad(100)
                    .denominacion("Detergente")
                    .precio(80)
                    .build();

            perecederos.getArticulos().add(art1);
            lacteos.getArticulos().add(art1);
            limpieza.getArticulos().add(art2);

            DetalleFactura det1 = DetalleFactura.builder()
                    .cantidad(2)
                    .subtotal(40)
                    .articulo(art1)
                    .build();

            art1.getDetalle().add(det1);
            factura1.getDetalles().add(det1);
            det1.setFactura(factura1);

            DetalleFactura det2 = DetalleFactura.builder()
                    .cantidad(1)
                    .subtotal(80)
                    .articulo(art2)
                    .build();

            art2.getDetalle().add(det2);
            factura1.getDetalles().add(det2);
            det2.setFactura(factura1);

            factura1.setTotal(120);

            //Factura factura1 = entityManager.find(Factura.class, 1L);
            //factura1.setNumero(85);


            entityManager.persist(factura1);
            entityManager.flush();
            entityManager.getTransaction().commit();

        }catch(Exception e){
            entityManager.getTransaction().rollback();
            System.out.println(e.getMessage());
            System.out.println("Error en la creación");
        }




        // Cerrar el EntityManager y el EntityManagerFactory
        entityManager.close();
        entityManagerFactory.close();
    }
}
