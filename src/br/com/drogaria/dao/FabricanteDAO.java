package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {
	public void salvar(Fabricante fabricante) throws SQLException {
		StringBuilder builder = new StringBuilder();
		builder.append("insert into fabricante ");
		builder.append("(descricao) ");
		builder.append("values (?)");
		Connection connection = ConexaoFactory.conectar();
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		preparedStatement.setString(1, fabricante.getDescricao());
		preparedStatement.executeUpdate();
	}

	public void excluir(Fabricante fabricante) throws SQLException {
		StringBuilder builder = new StringBuilder();
		builder.append("delete from fabricante ");
		builder.append("where codigo = ?");
		Connection connection = ConexaoFactory.conectar();
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		preparedStatement.setLong(1, fabricante.getCodigo());
		preparedStatement.executeUpdate();
	}

	public void editar(Fabricante fabricante) throws SQLException {
		StringBuilder builder = new StringBuilder();
		builder.append("update fabricante ");
		builder.append("set descricao = ? ");
		builder.append("where codigo = ?");
		Connection connection = ConexaoFactory.conectar();
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		preparedStatement.setString(1, fabricante.getDescricao());
		preparedStatement.setLong(2, fabricante.getCodigo());
		preparedStatement.executeUpdate();
	}

	public Fabricante buscarPorCodigo(Fabricante fabricante) throws SQLException {
		StringBuilder builder = new StringBuilder();
		builder.append("select codigo, descricao ");
		builder.append("from fabricante ");
		builder.append("where codigo = ?");
		Connection connection = ConexaoFactory.conectar();
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		preparedStatement.setLong(1, fabricante.getCodigo());
		ResultSet resultSet = preparedStatement.executeQuery();
		Fabricante retorno = null;
		if (resultSet.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resultSet.getLong("codigo"));
			retorno.setDescricao(resultSet.getString("descricao"));
		}
		return retorno;
	}

	public ArrayList<Fabricante> listar() throws SQLException {
		StringBuilder builder = new StringBuilder();
		builder.append("select codigo, descricao ");
		builder.append("from fabricante ");
		builder.append("order by descricao asc");
		Connection connection = ConexaoFactory.conectar();
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<Fabricante> arrayList = new ArrayList<Fabricante>();
		while (resultSet.next()) {
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultSet.getLong("codigo"));
			fabricante.setDescricao(resultSet.getString("descricao"));
			arrayList.add(fabricante);
		}
		return arrayList;
	}

	public ArrayList<Fabricante> buscarPorDescricao(Fabricante fabricante) throws SQLException {
		StringBuilder builder = new StringBuilder();
		builder.append("select codigo, descricao ");
		builder.append("from fabricante ");
		builder.append("where descricao like ? ");
		builder.append("order by descricao asc");
		Connection connection = ConexaoFactory.conectar();
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		preparedStatement.setString(1, "%" + fabricante.getDescricao() + "%");
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<Fabricante> arrayList = new ArrayList<Fabricante>();
		while (resultSet.next()) {
			Fabricante fabricante2 = new Fabricante();
			fabricante2.setCodigo(resultSet.getLong("codigo"));
			fabricante2.setDescricao(resultSet.getString("descricao"));
			arrayList.add(fabricante2);
		}
		return arrayList;
	}

	public static void main(String[] args) {
		/*
		 * Fabricante fabricante = new Fabricante();
		 * fabricante.setDescricao("DESCRIÇÃO 1"); Fabricante retorno = new
		 * Fabricante(); retorno.setDescricao("DESCRIÇÃO 2"); FabricanteDAO dao = new
		 * FabricanteDAO(); try { dao.salvar(fabricante); dao.salvar(retorno); } catch
		 * (SQLException e) { // TODO Auto-generated catch block e.printStackTrace(); }
		 */
		/*
		 * Fabricante fabricante = new Fabricante(); fabricante.setCodigo(2L);
		 * Fabricante retorno = new Fabricante(); retorno.setCodigo(5L); FabricanteDAO
		 * dao = new FabricanteDAO(); try { dao.excluir(fabricante);
		 * dao.excluir(retorno); } catch (SQLException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); }
		 */
		/*
		 * Fabricante fabricante = new Fabricante(); fabricante.setCodigo(3L);
		 * fabricante.setDescricao("DESCRIÇÃO 3"); FabricanteDAO dao = new
		 * FabricanteDAO(); try { dao.editar(fabricante); } catch (SQLException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); }
		 */
		/*
		 * Fabricante fabricante = new Fabricante(); fabricante.setCodigo(3L);
		 * Fabricante fabricante2 = new Fabricante(); fabricante2.setCodigo(5L);
		 * FabricanteDAO dao = new FabricanteDAO(); try { Fabricante fabricante3 =
		 * dao.buscarPorCodigo(fabricante); Fabricante fabricante4 =
		 * dao.buscarPorCodigo(fabricante2); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		/*
		 * FabricanteDAO dao = new FabricanteDAO(); try { ArrayList<Fabricante>
		 * arrayList = dao.listar(); for (Fabricante fabricante : arrayList) {
		 * System.out.println(fabricante); } } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		Fabricante fabricante = new Fabricante();
		fabricante.setDescricao("2");
		FabricanteDAO dao = new FabricanteDAO();
		try {
			ArrayList<Fabricante> arrayList = dao.buscarPorDescricao(fabricante);
			for (Fabricante fabricante2 : arrayList) {
				System.out.println(fabricante2);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
