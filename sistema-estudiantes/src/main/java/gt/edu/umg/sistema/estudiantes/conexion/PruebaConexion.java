/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.sistema.estudiantes.conexion;

import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        try (Connection conexion = Conexion.getConexion()) {

            System.out.println("CONEXIÓN EXITOSA A POSTGRESQL");

        } catch (Exception e) {

            System.out.println("ERROR DE CONEXIÓN");
            e.printStackTrace();
        }
    }
}