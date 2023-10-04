package com.thelocalmarketplace.hardware.test;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Currency;

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

import ca.ucalgary.seng300.simulation.SimulationException;
import powerutility.PowerGrid;

public class AbstractCoinDispenser_Test {
	//Initializing
	private PowerGrid power_grid;
	private CoinDispenser coin_dispenser;
	
	//a general coin that i can test with
	BigDecimal val = new BigDecimal(5);
	Coin coin = new Coin(Currency.getInstance("USD"), val);
	
	
	power_grid = PowerGrid.instance();
	coin_dispenser = new CoinDispenser(1);
	coin_dispenser.activate();
	coin_dispenser.connect(power_grid);
	
	@Test
	public void IDK() {
		CoinSlot coinslot = new CoinSlot();
		
		
	}
	
	
	
	
		
	}
	
	
	
	
}
