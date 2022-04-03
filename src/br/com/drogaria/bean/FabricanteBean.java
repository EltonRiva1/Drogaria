package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	private ArrayList<Fabricante> itens, itensFiltrados;
	private Fabricante fabricante;

	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}

	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			this.itens = dao.listar();
		} catch (SQLException exception) {
			exception.printStackTrace();
			JSFUtil.adicionarMensagemErro(exception.getMessage());
		}
	}

	public void prepararNovo() {
		this.fabricante = new Fabricante();
	}

	public void novo() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.salvar(this.fabricante);
			this.itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("O fabricante foi salvo com sucesso!");
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro(e.getMessage());
		}
	}

	public void excluir() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(this.fabricante);
			this.itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("O fabricante foi removido com sucesso!");
		} catch (SQLException exception) {
			exception.printStackTrace();
			JSFUtil.adicionarMensagemErro(exception.getMessage());
		}
	}

	public void editar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.editar(this.fabricante);
			this.itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("O fabricante foi editado com sucesso!");
		} catch (SQLException exception) {
			exception.printStackTrace();
			JSFUtil.adicionarMensagemErro(exception.getMessage());
		}
	}
}
