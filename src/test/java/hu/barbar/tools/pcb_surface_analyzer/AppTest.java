package hu.barbar.tools.pcb_surface_analyzer;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Ignore;
import org.junit.Test;

public class AppTest {

	@Ignore
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void getColorComponentSumTest(){
		//black
		int res = App.getColorComponentSum(new Color(-16777216));
		assertEquals(0, res);
		
		//white
		res = App.getColorComponentSum(new Color(-1));
		assertEquals(765, res);
		
	}

}
