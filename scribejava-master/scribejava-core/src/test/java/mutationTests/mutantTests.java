package mutationTests;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Base64.Encoder;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import org.junit.Assert;
import org.junit.Test;

import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.java8.Base64;
import com.github.scribejava.core.model.*;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.oauth.OAuthService;

public class mutantTests {
	
	//.core.model.OAuth1AccessToken tests 
	//5/6 mutants killed
	
    @Test //KILLS MUTANT.. Assures hash doesn't == 0, which it shouldn't (would make hash very guessable)
    public void line45ReplacedIntegerReturnWithTernary() {
    		OAuth1AccessToken oToken = new OAuth1AccessToken("Random", "String");
    		int actual = oToken.hashCode();
    		assertNotEquals(0,actual);
    }
    
    @Test //DOES NOT KILL MUTANT
    public void line50NegatedConditional() {
    		OAuth1AccessToken oToken = new OAuth1AccessToken("Random", "String");
    		boolean actual = oToken.equals(oToken);
    		assertTrue(actual);
    } 
    
    @Test //KILLS 4 MUTANTS.. assures hash is calculated properly. If hash equation changes, test will show it 
    public void line43ReplacedIntegerAdditionWithSubtraction() {
    		OAuth1AccessToken oToken = new OAuth1AccessToken("Random", "String");
    		int expected = 3;
        expected = 73 * expected + Objects.hashCode(oToken.getToken());
        expected = 73 * expected + Objects.hashCode(oToken.getTokenSecret());
		int actual = oToken.hashCode();
		assertEquals(expected,actual);
    }
    
    //.core.builder.ServiceBuilder tests
    //3/3 mutants killed
    
    @Test //KILLS 1 MUTANT.. assures exception is called when apikey() is passed empty string
    public void line42RemovedCall() {
    		Exception ex = null;
    		ServiceBuilder sBuilder = new ServiceBuilder("APIKEY");
    		try {
    			ServiceBuilder sBuilderReturned = sBuilder.apiKey("");
    		}catch (Exception e) {
    			ex = e;
    		}
    		if (ex.equals(null)) {
    			Assert.fail();
    		}
    }
    
    @Test //KILLS 1 MUTANT.. assures sBuilder returned is not null when apiKey parameter is not empty
    public void line44MutatedReturnToConditional() {
    		Exception ex = null;
    		ServiceBuilder sBuilderReturned = null;
    		ServiceBuilder sBuilder = new ServiceBuilder("APIKEY");
    		try {
    			sBuilderReturned = sBuilder.apiKey("APIKEY_NEW");
    		}catch (Exception e) {
    			ex = e;
    		}
    		assertNotEquals(sBuilderReturned,null);
    }
    
    @Test //KILLS 1 MUTANT.. assures exception is called when apiSecret() is passed empty string
    public void line49RemovedCall() {
 		Exception ex = null;
 		ServiceBuilder sBuilder = new ServiceBuilder("APIKEY");
 		try {
 			ServiceBuilder sBuilderReturned = sBuilder.apiSecret("");
 		}catch (Exception e) {
 			ex = e;
 		}
 		if (ex.equals(null)) {
 			Assert.fail();
 		}
    }
    
    //.core.model.OAuthToken tests
    //2/2 mutants killed
    
    	@Test //KILLS 1 MUTANT.. assures exception is called when null token passed to OAuth constructor
    	public void line18RemovedCall() {
    		OAuth1Token oToken = null;
    		Exception ex = null;
    		try {
    			oToken = new OAuth1AccessToken(null,"TOKENSECRET");
    		} catch (Exception e) {
    			ex = e;
    		}
    		if (ex.equals(null)) {
     		Assert.fail();
     	}
    	}
    	
    	@Test //KILLS 1 MUTANT.. assures exception is called when null token secret passed to OAuth constructor
    	public void line19RemovedCall() {
    		OAuth1Token oToken = null;
    		Exception ex = null;
    		try {
    			oToken = new OAuth1AccessToken("TOKEN",null);
    		} catch (Exception e) {
    			ex = e;
    		}
    		if (ex.equals(null)) {
     		Assert.fail();
     	}
    	}
    	
    	//.core.java8.Base64.Encoder
    	//4/19 mutants killed
    	
    	@Test //KILLED 4 Mutants.. assuring math is done correctly while encoding
    	public void multiLineReplacedDivisionWithMultiplication() {
    		byte[] b = "TEST".getBytes();
    		Base64.Encoder e = Base64.getEncoder();
    		byte[] a = e.encode(b);
    		//System.out.print("Length of initial: " + b.length + "\nLength of final: " + a.length);
    		assertEquals(a.length,b.length*2);
    	}
    	
    	@Test //KILLS NO MUTANTS.. changing length of test array here does not kill more mutants
    	public void lineOtherReplacedDivisionWithMultiplication() {
    		byte[] b = "TESTINGTESTING".getBytes();
    		Base64.Encoder e = Base64.getEncoder();
    		byte[] a = e.encode(b);
    		//System.out.print("Length of initial: " + b.length + "\nLength of final: " + a.length);
    		assertEquals(a.length,b.length+6);
    	}
    	
    	//.core.oauth.OAuthService
    	//0/4 mutants killed
    	
    	@Test //KILLS NO MUTANTS.. assures that http closure doesn't affect execute 
    	public void line56RemovedCall(){
    		OAuthService oService = new OAuth20Service(null, null, null, null, null, null, null, null, null, null);
    		Exception ex = null;
    		try {
    			oService.close();
    		} catch (Exception e) {
    			Assert.fail(); //close failed
    		}
    		try {
    			oService.execute(null, null, null);
    		} catch (Exception ee) {
    			ex = ee;
    		}
    		if (ex == null) {
    			Assert.fail(); //execute failed
    		}
    	}
    	
    	//.core.model.oAuthRequest 
    	//4/4 mutants killed
    	
    	@Test //KILLED 3 Mutants.. 1 In this class. Ensures all payloads are reset when string payload added
    	public void line264RemovedCall() {
    		File f = new File("what.txt");
    		OAuthRequest oRequest = new OAuthRequest(null, "URLTEST");
    		oRequest.setPayload(f);
    		oRequest.setPayload("PAYLOAD_NEW");

    		assertEquals(null,oRequest.getFilePayload());
    	}
    	
    @Test //KILLED 1 Mutant ..assures all payloads reset when File payload added
    	public void line284RemovedCall() {
    		File f = new File("what.txt");
    		OAuthRequest oRequest = new OAuthRequest(null, "URLTEST");
    		oRequest.setPayload("PAYLOAD_NEW");
    		oRequest.setPayload(f);

    		assertEquals(null,oRequest.getStringPayload());
    	}
    
 	@Test //KILLED 1 MUTANT ..assures getURL() doesn't return null when URL not null
	public void line328MutateReturn() {
 		
		OAuthRequest oRequest = new OAuthRequest(null, "URLTEST");
		String s = null;
		Exception ex = null;
		try {
			s = oRequest.getUrl();
		} catch (Exception e) {
			ex = e;
		}
		if (ex != null || s == null) {
			Assert.fail();
		}
	}
 	
 	@Test ////KILLED 1 MUTANT ..assures toString() doesn't return null when not null
	public void line377MutateReturn() {
 		
		OAuthRequest oRequest = new OAuthRequest(null, "URLTEST");
		String s = null;
		Exception ex = null;
		try {
			s = oRequest.toString();
		} catch (Exception e) {
			ex = e;
		}
		if (ex != null || s == null) {
			Assert.fail();
		}
 	}
		
	//.core.model.OAuth1AccessToken
	//1/1 mutant killed
		
	@Test ////KILLED 1 MUTANT ..assures check for null
	public void line69RemoveCall() {
 		Exception ex = null;
		try {
			OAuth2AccessToken oToken = new OAuth2AccessToken(null, null, null, null, null, null);
		}catch (Exception e) {
			ex = e;
		}
		if (ex == null) {
			Assert.fail();
		}
	}
 	
    	
    	
    	
 
}
