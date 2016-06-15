package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LeilaoTest {

	@Test
	public void naoDeveAceitarDoisLancesSeguidosDoMesmoUsuario() {
		Usuario ana = new Usuario("Ana");
		Leilao leilao = new Leilao("Leilão do carro");

		assertEquals(0, leilao.getLances().size());

		leilao.propoe(new Lance(ana, 400.00));
		leilao.propoe(new Lance(ana, 500.00));

		assertEquals(1, leilao.getLances().size());
		assertEquals(400, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Usuario ana = new Usuario("Ana");
		Usuario carlos = new Usuario("Carlos");
		Leilao leilao = new Leilao("Leilão do carro");

		leilao.propoe(new Lance(ana, 100.00));
		leilao.propoe(new Lance(carlos, 200.00));

		leilao.propoe(new Lance(ana, 200.00));
		leilao.propoe(new Lance(carlos, 300.00));

		leilao.propoe(new Lance(ana, 400.00));
		leilao.propoe(new Lance(carlos, 500.00));

		leilao.propoe(new Lance(ana, 600.00));
		leilao.propoe(new Lance(carlos, 700.00));

		leilao.propoe(new Lance(ana, 800.00));
		leilao.propoe(new Lance(carlos, 900.00));

		leilao.propoe(new Lance(ana, 1000.00));

		assertEquals(10, leilao.getLances().size());
		assertEquals(900, leilao.getLances().get(9).getValor(), 0.00001);
	}

	@Test
	public void deveDobrarOUltimoLanceDado() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario billGates = new Usuario("Bill Gates");

		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billGates, 3000));
		leilao.dobraLance(steveJobs);

		assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
	}

	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");

		leilao.dobraLance(steveJobs);

		assertEquals(0, leilao.getLances().size());
	}
}
