.
package de.freiburg_seminar.krypto;

import java.math.BigInteger;


/*
 * TODO: Wie werden Texte vor dem Chiffrieren kodiert?
 * TODO: Wie werden Schlüssel und andere Binärdaten gespeichert
 * (BASE64, hex?)
 * 
 * TODO: Woher kommt der Zufall? 
 */
public class RSA {

	public class Key
	{
		/**
		 * 
		 */
		public Key(File) {
		};
		
		/**
		 * 
		 */
		public Key(BigInteger N, BigInteger exponent) {
		};

		private BigInteger exponent;
		private BigInteger N;

		public BigInteger getExponent();
		public BigInteger getN();	
		
		public void save(File);
	};
	
	public class KeyPair
	{
		private Key priv ;
		private Key pub;
		
		public KeyPair(File);
		public KeyPair(Key priv, Key pub);
		public void save(File);
		public Key getPriv();
		public Key getpub();
		
	};

	public final int KeySize = 100;
	
	public KeyPair createKey() {
		// TODO: Methode implementieren 
	}
	
	public pubKey extractpubKey();
	
	public BigInteger chif(BigInteger message, BigInteger pub);
	public BigInteger dech(BigInteger message, BigInteger pub);
	public BigInteger sign(BigInteger message, BigInteger priv);
	public BigInteger veri(BigInteger message, BigInteger priv);
	
	
	
}
