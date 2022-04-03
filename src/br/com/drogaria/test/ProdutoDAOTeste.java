package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTeste {
	@Test
	@Ignore
	public void salvar() throws SQLException {
		Produto produto = new Produto();
		produto.setDescricao("Novalgina com 10 comprimidos");
		produto.setDecimal(2.45D);
		produto.setQuantidade(13L);
		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(9L);
		produto.setFabricante(fabricante);
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(produto);
	}

	@Test
	@Ignore
	public void listar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		ArrayList<Produto> arrayList = dao.listar();
		for (Produto produto : arrayList) {
			System.out.println("Código do Produto: " + produto.getCodigo());
			System.out.println("Descrição do Produto: " + produto.getDescricao());
			System.out.println("Preço: " + produto.getDecimal());
			System.out.println("Quantidade: " + produto.getQuantidade());
			System.out.println("Código do Fabricante: " + produto.getFabricante().getCodigo());
			System.out.println("Descrição do Fabricante: " + produto.getFabricante().getDescricao());
			System.out.println();
		}
	}

	@Test
	@Ignore
	public void excluir() throws SQLException {
		Produto produto = new Produto();
		produto.setCodigo(6L);
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(produto);
	}

	@Test
	public void editar() throws SQLException {
		Produto produto = new Produto();
		produto.setCodigo(7L);
		produto.setDescricao("Cataflan Pomada com 60 Gramas");
		produto.setDecimal(15.30D);
		produto.setQuantidade(7L);
		Fabricante fabricante = new Fabricante();
		fabricante.setCodigo(19L);
		produto.setFabricante(fabricante);
		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(produto);
	}
}
