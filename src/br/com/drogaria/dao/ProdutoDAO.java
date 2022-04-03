package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.factory.ConexaoFactory;

public class ProdutoDAO {
	public void salvar(Produto produto) throws SQLException {
		StringBuilder builder = new StringBuilder();
		builder.append("insert into produto ");
		builder.append("(descricao,preco,quantidade,fabricante_codigo) ");
		builder.append("values (?,?,?,?)");
		Connection connection = ConexaoFactory.conectar();
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		preparedStatement.setString(1, produto.getDescricao());
		preparedStatement.setDouble(2, produto.getDecimal());
		preparedStatement.setLong(3, produto.getQuantidade());
		preparedStatement.setLong(4, produto.getFabricante().getCodigo());
		preparedStatement.executeUpdate();
	}

	public ArrayList<Produto> listar() throws SQLException {
		StringBuilder builder = new StringBuilder();
		builder.append("select p.codigo,p.descricao,p.preco,p.quantidade,f.codigo,f.descricao ");
		builder.append("from produto p ");
		builder.append("inner join fabricante f on f.codigo=p.fabricante_codigo");
		Connection connection = ConexaoFactory.conectar();
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		ResultSet resultSet = preparedStatement.executeQuery();
		ArrayList<Produto> arrayList = new ArrayList<Produto>();
		while (resultSet.next()) {
			Fabricante fabricante = new Fabricante();
			fabricante.setCodigo(resultSet.getLong("f.codigo"));
			fabricante.setDescricao(resultSet.getString("f.descricao"));
			Produto produto = new Produto();
			produto.setCodigo(resultSet.getLong("p.codigo"));
			produto.setDescricao(resultSet.getString("p.descricao"));
			produto.setDecimal(resultSet.getDouble("p.preco"));
			produto.setQuantidade(resultSet.getLong("p.quantidade"));
			produto.setFabricante(fabricante);
			arrayList.add(produto);
		}
		return arrayList;
	}

	public void excluir(Produto produto) throws SQLException {
		StringBuilder builder = new StringBuilder();
		builder.append("delete from produto ");
		builder.append("where codigo=?");
		Connection connection = ConexaoFactory.conectar();
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		preparedStatement.setLong(1, produto.getCodigo());
		preparedStatement.executeUpdate();
	}

	public void editar(Produto produto) throws SQLException {
		StringBuilder builder = new StringBuilder();
		builder.append("update produto ");
		builder.append("set descricao=?,preco=?,quantidade=?,fabricante_codigo=? ");
		builder.append("where codigo=?");
		Connection connection = ConexaoFactory.conectar();
		PreparedStatement preparedStatement = connection.prepareStatement(builder.toString());
		preparedStatement.setString(1, produto.getDescricao());
		preparedStatement.setDouble(2, produto.getDecimal());
		preparedStatement.setLong(3, produto.getQuantidade());
		preparedStatement.setLong(4, produto.getFabricante().getCodigo());
		preparedStatement.setLong(5, produto.getCodigo());
		preparedStatement.executeUpdate();
	}
}
