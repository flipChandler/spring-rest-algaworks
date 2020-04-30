package com.algaworks.osworks.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class OrdemServicoInputModel {
	// VALIDANDO O REPRESENTATIONAL MODEL, ASSIM NÃO É NECESSÁRIO MANTER AS VALIDAÇÕES NO DOMAIN
	
	@NotBlank
	private String descricao;
	
	@NotNull
	private BigDecimal preco;
	
	@Valid // FAZER O CASCATEAMENTO DAS VALIDAÇÕES
	@NotNull
	private ClienteIdInput cliente;
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public ClienteIdInput getCliente() {
		return cliente;
	}
	public void setCliente(ClienteIdInput cliente) {
		this.cliente = cliente;
	}	
	
}
