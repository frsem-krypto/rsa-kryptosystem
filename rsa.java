package de.freiburg_seminar.krypto;

import java.math.BigInteger;
import java.io.*;


/*
 * TODO: Wie werden Texte vor dem Chiffrieren kodiert?
 * TODO: Wie werden Schlüssel und andere Binärdaten gespeichert
 * (BASE64, hex?)
 * 
 * TODO: Woher kommt der Zufall? 
 * 
 * TODO Main() schreiben
 */
public class RSA {

	public class Schluessel
	{
		/**
		 * 
		 */
		public Schluessel(File file) {
			//TODO Schluessel laden
		}
		
		/**
		 * 
		 */
		public Schluessel(BigInteger N, BigInteger exponent) {
			this.N = N;
			this.exponent = exponent;
		};

		private BigInteger exponent;
		private BigInteger N;

		public BigInteger getExponent(){
			return exponent;
		}
		public BigInteger getN(){
			return N;
		}
		
		public void speichere(File file){
			//TODO Schluessel speichern
		}
	};
	
	public class Schluesselpaar
	{
		private Schluessel priv ;
		private Schluessel oeff;
		
		public Schluesselpaar(File file){
			//TODO Die abgespeicherten Schluessel muessen in der Endung eine Info enthalten, ob sie privat oder oeffentlich sind. Dazu muessen sie es zuerst selbst wissen (mithilfe eines Booleans?) 
		}
		public Schluesselpaar(Schluessel privat, Schluessel oeffentlich){
			priv = privat;
			oeff = oeffentlich;
		}
		
		public void speichere(File file){
			//TODO Soll stattdessen ein Ordner angelegt werden, der privaten und oeffentlichen Schluessen enthaelt? Oder beide in eine Textdatei geschrieben werden? 
			priv.speichere(file);
			oeff.speichere(file);
		}
		public Schluessel getPriv(){
			return priv;
		}
		public Schluessel getOeff(){
			return oeff;
		}
		
	};

	public final int schluesselGroesse = 100;
	
	public Schluesselpaar erzeugeSchluessel() {
		// TODO: Methode implementieren 
	}
	
	public Schluessel extrahiereOeffSchluessel(){
		//TODO Methode implementieren
	}
	
	public BigInteger chif(BigInteger message, BigInteger oeff){
		//TODO chiffrieren
	}
	public BigInteger dech(BigInteger message, BigInteger oeff){
		//TODO dechiffrieren
	}
	public BigInteger sign(BigInteger message, BigInteger priv){
		//TODO signieren
	}
	public BigInteger veri(BigInteger message, BigInteger priv){
		//TODO verifizieren
	}
	
	
}
