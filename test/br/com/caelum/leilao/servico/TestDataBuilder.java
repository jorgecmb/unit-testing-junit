package br.com.caelum.leilao.servico;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class TestDataBuilder {

	private Leilao leilao;

	public TestDataBuilder para(String nome) {
		this.leilao = new Leilao("Leilão do carro");
		return this;
	}
	
	public TestDataBuilder lance(Usuario usuario, Double valor){
		leilao.propoe(new Lance(usuario, valor));
		return this;
	}
	
	public Leilao constroi() {
		return leilao;
	}
}
