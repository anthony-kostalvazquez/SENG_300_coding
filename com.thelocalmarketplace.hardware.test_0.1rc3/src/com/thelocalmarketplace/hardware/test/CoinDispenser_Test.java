/* BUGS IN COIN DISPENSER
 * 
 * 1. the hasSpace() documentation says that it requires power to work, however this is not true
 * 
 * 
 * 
 * 
 * 
 * 
 */







package com.thelocalmarketplace.hardware.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.tdc.CashOverloadException;
import com.tdc.DisabledException;
import com.tdc.NoCashAvailableException;
import com.tdc.coin.AbstractCoinDispenser;
import com.tdc.coin.Coin;
import com.tdc.coin.CoinDispenser;
import com.tdc.coin.CoinSlot;
import com.tdc.coin.CoinValidator;

import ca.ucalgary.seng300.simulation.SimulationException;
import powerutility.NoPowerException;
import powerutility.PowerGrid;

public class CoinDispenser_Test {
	//initalizing
	private PowerGrid power_grid;
	private CoinDispenser coin_dispenser;
	
	//a general coin that i can test with
	BigDecimal val = new BigDecimal(5);
	Coin coin = new Coin(Currency.getInstance("USD"), val);
	
	//constructor tests
	@Test
	public void test_CreateCoinDispenser() {
		this.coin_dispenser = new CoinDispenser(500);
		assertTrue( coin_dispenser.getCapacity() == 500 );
	}

	
	@Test(expected = SimulationException.class)
	public void test_CoinDispenserWithNegativeCapacity() 
	{
		this.coin_dispenser = new CoinDispenser(-500);
	}
	
	
	
	//receive tests
	@Before
	public void setup(){
		power_grid = PowerGrid.instance();
		this.coin_dispenser = new CoinDispenser(1);
		this.coin_dispenser.activate();
		this.coin_dispenser.connect(power_grid);
		
	}

	
	@Test
	public void test_ReciveCoinToCoinDispeser() throws CashOverloadException, DisabledException{
		coin_dispenser.receive(coin);
		
		Assert.assertEquals(1, coin_dispenser.size());
		
	}
	
	
	@Test(expected = SimulationException.class)
	public void test_AddNullCoinToCoinDispenser() throws CashOverloadException, DisabledException{
		coin_dispenser.receive(null);
	}
	
	
	@Test(expected = DisabledException.class)
	public void test_AddCoinToDisabledCoinDispenser() throws CashOverloadException, DisabledException{
		
		coin_dispenser.disable();
		coin_dispenser.receive(coin);
	}
	
	
	@Test (expected = CashOverloadException.class)
	public void test_AddCoinToFullCoinDispenser() throws CashOverloadException, DisabledException{

		coin_dispenser.receive(coin);
		coin_dispenser.receive(coin);
		
	}
	
	//has space tests
	@Test
	public void test_CoinDispenserHasSpace() throws CashOverloadException, DisabledException {
		//has 1 space left
		Assert.assertEquals(true, coin_dispenser.hasSpace());
		
		coin_dispenser.receive(coin);
		
		//has no space left
		Assert.assertEquals(false, coin_dispenser.hasSpace());
		
	}
	
	@Test
	public void test_CoinDispenserNoPowerHasSpace() {
		coin_dispenser.disconnect();
		Assert.assertEquals(true, coin_dispenser.hasSpace());
		
		
	}
	
	//reject tests
	@Test(expected = NoPowerException.class)
	public void test_RejectCoinFromSinkWhileDeactivated() throws DisabledException, CashOverloadException{
		//put coin in output sink
		coin_dispenser.disactivate();
		coin_dispenser.reject(coin);
		
		
	}
	
	@Test
	public void test_ReciveCoinFromOutputSink() throws CashOverloadException, DisabledException {
		coin_dispenser.sink
		
		
	}
	
	
	
	
}
