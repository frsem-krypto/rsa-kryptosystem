import java.math.BigInteger;
import java.util.Random;
import java.io.*;


/*
 * TODO: Wie werden Texte vor dem Chiffrieren kodiert?
 * TODO: Wie werden Schlaessel und andere Binaerdaten gespeichert
 * (BASE64, hex?)
 * 
 * TODO: Woher kommt der Zufall? 
 */
public class rsa {
	public static class Key {
		/**
		 * 
		 */
		public Key(File file) {
		};
		
		/**
		 * 
		 */
		
		private BigInteger exponent;
		private BigInteger N;
		
		public Key(BigInteger N, BigInteger exponent) {
			this.N = N;
			this.exponent = exponent;
		};


		
		public BigInteger getExponent(){
			return exponent;
		}
		public BigInteger getN(){
			return N;
		}
		
		public void save(File file){
			//TODO Key abspeichern
			//Hierzu kann dateiEndung() verwendet werden
		}
	}
	
	public static class KeyPair
	{
		private Key priv ;
		private Key pub;
		
		public KeyPair(File file) {
		
		}
		public KeyPair(Key priv, Key pub) {
			this.priv = priv;
			this.pub = pub;
		}
		public void save(File file) {
		
		}
		public Key getPriv() {
			return priv;
		}
		public Key getPub() {
			return pub;
		}
		
	};

	public final int KeySize = 100;
	

	
	public Key  extractpubKey() {
		return null;
	}
	
	
	public BigInteger chif(BigInteger message, Key oeff){
		return message.modPow(oeff.getExponent(), oeff.getN());
	}
	public BigInteger dech(BigInteger message, Key priv){
		return message.modPow(priv.getExponent(), priv.getN());
	}
	public BigInteger sign(BigInteger message, Key priv){
		return message.modPow(priv.getExponent(), priv.getN());
	}
	public BigInteger veri(BigInteger message, Key oeff){
		return message.modPow(oeff.getExponent(), oeff.getN());
	}
	
		
	public static KeyPair createKeyPair(int bitLength, int certainly){
		BigInteger p = new BigInteger(bitLength, certainly, newRandom());
		BigInteger q = new BigInteger(bitLength, certainly, newRandom());
		
		BigInteger n = p.multiply(q);
		
		BigInteger phiVonN = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
		
		BigInteger e = new BigInteger(bitLength, newRandom());
		while (e.gcd(phiVonN).equals(BigInteger.ONE) == false){
			e = e.add(BigInteger.ONE);
		}
		
		BigInteger d = e.modInverse(phiVonN);
		
		return new KeyPair(new Key(d, n), new Key(e, n));
	}

	private static Random newRandom() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
