import java.math.BigInteger;
import junit.framework.TestCase;


public class rsaTest extends TestCase {

	//	public void testChif() {
	//		fail("Not yet implemented");
	//	}
	//
	//	public void testDech() {
	//		fail("Not yet implemented");
	//	}
	//
	//	public void testSign() {
	//		fail("Not yet implemented");
	//	}
	//
	//	public void testVeri() {
	//		fail("Not yet implemented");
	//	}

	public void testCreateKeyPair() {

		for(int i = 0; i < 100000; i++){
			rsa.KeyPair key = rsa.createKeyPair(100, 100);

			assertNotNull(key);
			assertNotNull(key.getPriv());
			assertNotNull(key.getPub());

			BigInteger d = key.getPriv().getExponent();
			//		assertEquals(37, d.longValue());
			//		
			BigInteger e = key.getPub().getExponent();

			//		
			BigInteger nPub = key.getPub().getN();
			BigInteger nPriv = key.getPriv().getN();

//			System.out.println(nPub.compareTo(nPriv));
//			System.out.println(nPriv.compareTo(nPub));
//			System.out.println(nPub.equals(nPriv));
			assertEquals(nPub, nPriv);
			//		assertEquals(77, nPriv.longValue());

			BigInteger message = new BigInteger("Hallo!".getBytes());

//			System.out.println("message = " + message);

			BigInteger encrypted = rsa.chif(message, key.getPub());

//			System.out.println("encrypted: " + encrypted);

			BigInteger decrypted = rsa.dech(encrypted, key.getPriv());

			assertEquals(message, decrypted);

//			System.out.println(new String(decrypted.toByteArray()));

			assertEquals("Hallo!", new String(decrypted.toByteArray())); 	

		}

	}

}
