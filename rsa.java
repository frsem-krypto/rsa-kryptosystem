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

	public class Schluessel
	{
		/**
		 * 
		 */
		public Schluessel(File) {
		};
		
		/**
		 * 
		 */
		public Schluessel(BigInteger N, BigInteger exponent) {
		};

		private BigInteger exponent;
		private BigInteger N;

		public BigInteger getExponent();
		public BigInteger getN();	
		
		public void speichere(File);
	};
	
	public class Schluesselpaar
	{
		private Schluessel priv ;
		private Schluessel oeff;
		
		public Schluesselpaar(File);
		public Schluesselpaar(Schluessel priv, Schluessel oeff);
		public void speichere(File);
		public Schluessel getPriv();
		public Schluessel getOeff();
		
	};

	public final int schluesselGroesse = 100;
	
	public Schluesselpaar erzeugeSchluessel() {
		// TODO: Methode implementieren 
	}
	
	public OeffentlicherSchluessel extrahiereOeffSchluessel();
	
	public BigInteger chif(BigInteger message, BigInteger oeff);
	public BigInteger dech(BigInteger message, BigInteger oeff);
	public BigInteger sign(BigInteger message, BigInteger priv);
	public BigInteger veri(BigInteger message, BigInteger priv);
	
	
	
}
