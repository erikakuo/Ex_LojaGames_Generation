package org.generation.lojagames.lojagames.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name= "tb_categoria")
public class CategoriaModel {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private long id;
	@NotNull
	@Size(min = 5, max = 20)
	private String genero;
	@NotNull
	@Size(min = 5, max = 500)
	private String nome;
	@NotNull
	@Size(min = 5, max = 100)
	private double preco;
	@NotNull
	@Size(min = 5, max = 10)
	private int versao;
	@NotNull
	@Size(min = 5, max = 20)
	
	@ManyToOne
	@JsonIgnoreProperties("tb_categoria")
	private ProdutoModel produto;
	
	private String descricao;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getVersao() {
		return versao;
	}
	public void setVersao(int versao) {
		this.versao = versao;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public ProdutoModel getProduto() {
		return produto;
	}

	public void setProduto(ProdutoModel produto) {
		this.produto = produto;
	}
}
	