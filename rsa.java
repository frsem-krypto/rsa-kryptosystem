import java.math.BigInteger;
import java.io.*;


/*
 * TODO: Wie werden Texte vor dem Chiffrieren kodiert?
 * TODO: Wie werden Schlaessel und andere Binaerdaten gespeichert
 * (BASE64, hex?)
 * 
 * TODO: Woher kommt der Zufall? 
 */
public class rsa {
	public class Key
	{
		/**
		 * 
		 */
		public Key(File file) {
		};
		
		/**
		 * 
		 */
		public Key(BigInteger N, BigInteger exponent) {
		};

		private BigInteger exponent;
		private BigInteger N;
		public BigInteger getExponent(){
			return exponent;
		}
		public BigInteger getN(){
			return N;
		}
		
		public void save(File file){
			//TODO Schluessel abspeichern
			//Hierzu kann dateiEndung() verwendet werden
		}
	}
	
	public class KeyPair
	{
		private Key priv ;
		private Key pub;
		
		public KeyPair(File file) {
		
		}
		public KeyPair(Key priv, Key pub) {
		
		}
		public void save(File file) {
		
		}
		public Key getPriv() {
			return null;
		}
		public Key getpub() {
			return null;
		}
		
	};

	public final int KeySize = 100;
	
	public KeyPair createKey() {
		// TODO: Methode implementieren 
			return null;
	}
	
	public Key  extractpubKey() {
		return null;
	}
	
	public BigInteger chif(BigInteger message, BigInteger pub) {
		return null;	
	}
	
	public BigInteger dech(BigInteger message, BigInteger pub) {
		return null;
	}
	public BigInteger sign(BigInteger message, BigInteger priv) {
		return null;
	}
	
	public BigInteger veri(BigInteger message, BigInteger priv) {
		return null;
	}
	
}
