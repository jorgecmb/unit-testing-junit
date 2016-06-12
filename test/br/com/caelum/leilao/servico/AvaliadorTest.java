package br.com.caelum.leilao.servico;

import junit.framework.Assert;

import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;
import br.com.caelum.leilao.servico.Avaliador;

@SuppressWarnings("deprecation")
public class AvaliadorTest {
	@Test
	public void testaMenorMaiorLance() {
		Leilao leilao = new Leilao("Leilão do carro");

		Usuario carlos = new Usuario("Carlos");
		Usuario ana = new Usuario("Ana");
		Usuario tobias = new Usuario("Tobias");
		
		leilao.propoe(new Lance(carlos, 800.00));
		leilao.propoe(new Lance(ana, 500.00));
		leilao.propoe(new Lance(tobias, 900.00));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(900, leiloeiro.getMaiorLance(), 0.00001);
		Assert.assertEquals(500, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void testaMedia(){
		Leilao leilao = new Leilao("Leilão do carro");

		Usuario carlos = new Usuario("Carlos");
		Usuario ana = new Usuario("Ana");
		Usuario tobias = new Usuario("Tobias");
		
		leilao.propoe(new Lance(carlos, 800.00));
		leilao.propoe(new Lance(ana, 700.00));
		leilao.propoe(new Lance(tobias, 900.00));
		
		Avaliador leiloeiro = new Avaliador();
		leiloeiro.avalia(leilao);
		
		Assert.assertEquals(800, leiloeiro.getMedia(), 0.00001);
	}
}
