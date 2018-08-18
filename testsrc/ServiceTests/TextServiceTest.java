package ServiceTests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dp.concordancer.ConcFacade.ConcFacadeImpl;
import dp.concordancer.ConcFacade.ConcordancerFacade;
import dp.concordancer.ConcFacade.TextService;
import dp.model.concordancer.KWICInterface;
import dp.model.concordancer.Kwic;

class TextServiceTest {
	private static TextService textservice = new TextService();

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void Kwictest() {
		
		
		String text = "Love looks not with the eyes, but with the mind, And therefore is wing’d Cupid painted blind.";
		String filename = "Shakespeare";
		String query = "CuPid";//check permutations as well
		String expected = "is wing’d Cupid painted b";
		int context = 10;
		List<KWICInterface> words = textservice.getKwic(text, filename, query, context);
		KWICInterface result = words.get(0);
		assertEquals(expected, result.toString());
		
		
	}

}
