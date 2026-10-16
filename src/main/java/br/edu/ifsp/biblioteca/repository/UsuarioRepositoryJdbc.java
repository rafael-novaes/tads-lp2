package br.edu.ifsp.biblioteca.repository;

import br.edu.ifsp.biblioteca.domain.Usuario;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@Profile("jdbc")
public class UsuarioRepositoryJdbc implements IUsuarioRepository{

    private final DataSource dataSource;

    public UsuarioRepositoryJdbc(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Usuario salvar(Usuario usuario) {

        String sql = "INSERT INTO Usuario (nome, email) VALUES (?, ?)";


        try (Connection connection = dataSource.getConnection()) {

            PreparedStatement comando = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            comando.setString(1, usuario.getNome());
            comando.setString(2, usuario.getEmail());

            comando.executeUpdate();

            try (ResultSet rs = comando.getGeneratedKeys()){
                while (rs.next()) {
                    usuario.setId(rs.getLong(1));
                }
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Falha ao salvar o usuário", e);
        }

        return usuario;
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return this.buscarUm("SELECT * FROM usuario WHERE id = ?", id);
    }

    @Override
    public Optional<Usuario> buscarPorEmail(String email) {
        return this.buscarUm("SELECT * FROM usuario WHERE LOWER(email) = LOWER(?)", email);
    }

    @Override
    public List<Usuario> listarTodos() {

        String sql = "SELECT * FROM Usuario ORDER BY nome";

        try (Connection connection = this.dataSource.getConnection()){

            PreparedStatement comando = connection.prepareStatement(sql);
            ResultSet resultSet = comando.executeQuery();

            List<Usuario> usuarios = new ArrayList<>();

            while (resultSet.next()) {
                usuarios.add(this.mapear(resultSet));
            }

            return usuarios;

        } catch (SQLException e) {
            throw new IllegalStateException("Falha ao listar usuários", e);
        }

    }

    private Usuario mapear (ResultSet resultSet) throws SQLException {

        Usuario usuario = new Usuario(resultSet.getString("nome"), resultSet.getString("email"));
        usuario.setId(resultSet.getLong("id"));

        return usuario;

    }

    private Optional<Usuario> buscarUm (String sql, Object parametro) {
        try (Connection connection = this.dataSource.getConnection()){

            PreparedStatement comando = connection.prepareStatement(sql);

            comando.setObject(1, parametro);

            try (ResultSet rs = comando.executeQuery()){
                while (rs.next()) {
                    return Optional.of(this.mapear(rs));
                }
                return Optional.empty();
            }

        } catch (SQLException e) {
            throw new IllegalStateException("Falha ao consultar usuário!", e);
        }
    }
}
