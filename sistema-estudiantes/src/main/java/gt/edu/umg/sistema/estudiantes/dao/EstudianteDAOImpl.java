 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gt.edu.umg.sistema.estudiantes.dao;

import gt.edu.umg.sistema.estudiantes.conexion.Conexion;
import gt.edu.umg.sistema.estudiantes.modelo.Estudiante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudianteDAOImpl implements EstudianteDAO {

    @Override
    public void guardar(Estudiante estudiante) {

        String sql = """
                INSERT INTO estudiantes
                (nombres, apellidos, carnet, email)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombres());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3, estudiante.getCarnet());
            ps.setString(4, estudiante.getEmail());

            ps.executeUpdate();

            System.out.println("Estudiante guardado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al guardar estudiante.");
            e.printStackTrace();
        }
    }

    @Override
    public List<Estudiante> listar() {

        List<Estudiante> estudiantes = new ArrayList<>();

        String sql = """
                SELECT id, nombres, apellidos, carnet, email
                FROM estudiantes
                ORDER BY id
                """;

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Estudiante estudiante = new Estudiante();

                estudiante.setId(rs.getInt("id"));
                estudiante.setNombres(rs.getString("nombres"));
                estudiante.setApellidos(rs.getString("apellidos"));
                estudiante.setCarnet(rs.getString("carnet"));
                estudiante.setEmail(rs.getString("email"));

                estudiantes.add(estudiante);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar estudiantes.");
            e.printStackTrace();
        }

        return estudiantes;
    }

    @Override
    public void actualizar(Estudiante estudiante) {

        String sql = """
                UPDATE estudiantes
                SET nombres = ?,
                    apellidos = ?,
                    carnet = ?,
                    email = ?
                WHERE id = ?
                """;

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setString(1, estudiante.getNombres());
            ps.setString(2, estudiante.getApellidos());
            ps.setString(3, estudiante.getCarnet());
            ps.setString(4, estudiante.getEmail());
            ps.setInt(5, estudiante.getId());

            ps.executeUpdate();

            System.out.println("Estudiante actualizado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al actualizar estudiante.");
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int id) {

        String sql = "DELETE FROM estudiantes WHERE id = ?";

        try (Connection conexion = Conexion.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql)) {

            ps.setInt(1, id);

            ps.executeUpdate();

            System.out.println("Estudiante eliminado correctamente.");

        } catch (SQLException e) {
            System.out.println("Error al eliminar estudiante.");
            e.printStackTrace();
        }
    }
}