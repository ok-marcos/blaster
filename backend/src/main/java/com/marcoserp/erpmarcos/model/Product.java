package com.marcoserp.erpmarcos.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "tb_products")
public class Product {
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    private String name;
    private Double precoCompra;
    private Double precoVenda;
    private String descricao;
	public Product() {
		
	}
	public Product(Long id, String name, Double precoCompra, Double precoVenda, String descricao) {
		this.id = id;
		this.name = name;
		this.precoCompra = precoCompra;
		this.precoVenda = precoVenda;
		this.descricao = descricao;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "preco_compra", nullable = false)
	public Double getPrecoCompra() {
		return precoCompra;
	}
	public void setPrecoCompra(Double precoCompra) {
		this.precoCompra = precoCompra;
	}
	@Column(name = "preco_venda", nullable = false)
	public Double getPrecoVenda() {
		return precoVenda;
	}
	public void setPrecoVenda(Double precoVenda) {
		this.precoVenda = precoVenda;
	}
	@Column(name = "descricao", nullable = false)
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", precoCompra=" + precoCompra + ", precoVenda=" + precoVenda
				+ ", descricao=" + descricao + "]";
	}
	
    
    
}
