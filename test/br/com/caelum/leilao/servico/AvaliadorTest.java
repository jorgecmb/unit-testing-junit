package br.com.caelum.leilao.servico;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.com.caelum.leilao.dominio.Lance;
import br.com.caelum.leilao.dominio.Leilao;
import br.com.caelum.leilao.dominio.Usuario;

public class AvaliadorTest {
	
	private Avaliador leiloeiro;
	private Usuario carlos;
	private Usuario ana;
	private Usuario tobias;

	@Before
	public void setUp(){
		this.leiloeiro = new Avaliador();
		this.carlos = new Usuario("Carlos");
		this.ana = new Usuario("Ana");
		this.tobias = new Usuario("Tobias");
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
	    Leilao leilao = new TestDataBuilder()
	        .para("Playstation 3 Novo")
	        .constroi();

	    leiloeiro.avalia(leilao);
	}
	
	@Test
	public void testaMenorMaiorLance() {
		Leilao leilao = new TestDataBuilder()
		.para("Leilão do carro")
		.lance(carlos, 800.00)
		.lance(ana, 500.00)
		.lance(tobias, 900.00)
		.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(900, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(500, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void testaUmLance(){
		Leilao leilao = new TestDataBuilder()
		.para("Leilão do carro")
		.lance(carlos, 800.00)
		.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(800, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(800, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void testaLancesRandomicos(){
		Leilao leilao = new TestDataBuilder()
		.para("Leilão do carro")
		.lance(carlos, 200.00)
		.lance(ana, 450.00)
		.lance(carlos, 120.00)
		.lance(ana, 700.00)
		.lance(carlos, 630.00)
		.lance(ana, 230.00)
		.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(700, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(120, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void testaLancesDecrescentes(){
		Leilao leilao = new TestDataBuilder()
		.para("Leilão do carro")
		.lance(carlos, 400.00)
		.lance(ana, 300.00)
		.lance(carlos, 200.00)
		.lance(ana, 100.00)
		.constroi();
		
		leiloeiro.avalia(leilao);
		
		assertEquals(400, leiloeiro.getMaiorLance(), 0.00001);
		assertEquals(100, leiloeiro.getMenorLance(), 0.00001);
	}
	
	@Test
	public void testaTresMaiores(){
		Leilao leilao = new TestDataBuilder()
		.para("Leilão do carro")
		.lance(carlos, 400.00)
		.lance(ana, 300.00)
		.lance(carlos, 200.00)
		.lance(ana, 100.00)
		.lance(carlos, 500.00)
		.constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		
		assertEquals(500, tresMaiores.get(0).getValor(), 0.00001);
		assertEquals(400, tresMaiores.get(1).getValor(), 0.00001);
		assertEquals(300, tresMaiores.get(2).getValor(), 0.00001);
		assertEquals(3, tresMaiores.size(), 0.00001);
	}
	
	@Test
	public void testaDoisMaiores(){
		Leilao leilao = new TestDataBuilder()
		.para("Leilão do carro")
		.lance(carlos, 400.00)
		.lance(ana, 300.00)
		.constroi();
		
		leiloeiro.avalia(leilao);
		
		List<Lance> tresMaiores = leiloeiro.getTresMaiores();
		
		assertEquals(400, tresMaiores.get(0).getValor(), 0.00001);
		assertEquals(300, tresMaiores.get(1).getValor(), 0.00001);
		assertEquals(2, tresMaiores.size(), 0.00001);
	}
	
}
