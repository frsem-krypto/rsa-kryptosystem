import java.math.BigInteger;
import java.io.*;


/*
 * TODO: Wie werden Texte vor dem Chiffrieren kodiert?
 * TODO: Wie werden SchlÃ¼ssel und andere BinÃ¤rdaten gespeichert
 * (BASE64, hex?)
 * 
 * TODO: Woher kommt der Zufall? 
 * 
 * TODO Main() schreiben
 */
public class rsa {

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
			//TODO Schluessel abspeichern
			//Hierzu kann dateiEndung() verwendet werden
		}
	}
	

	
	
	public class Schluesselpaar
	{
		private Schluessel priv ;
		private Schluessel oeff;
		
		public Schluesselpaar(File file){
			//TODO Schluesselpaar einlesen
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
		return null;
		// TODO: Methode implementieren 
	}
	
	public Schluessel extrahiereOeffSchluessel(){
		return null;
		//TODO Methode implementieren
	}
	
	public BigInteger chif(BigInteger message, Schluessel oeff){
		return moduluPotenz(message, oeff.getExponent(), oeff.getN());
	}
	public BigInteger dech(BigInteger message, Schluessel priv){
		return moduluPotenz(message, priv.getExponent(), priv.getN());
	}
	public BigInteger sign(BigInteger message, Schluessel priv){
		return moduluPotenz(message, priv.getExponent(), priv.getN());
	}
	public BigInteger veri(BigInteger message, Schluessel oeff){
		return moduluPotenz(message, oeff.getExponent(), oeff.getN());
	}
	
	public BigInteger moduluPotenz(BigInteger basis, BigInteger potenz, BigInteger modulu){
		if (0 == potenz.compareTo(BigInteger.valueOf(1))){
			return basis;
			
		}else if (potenz.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
			BigInteger halbePotenz = moduluPotenz(basis, potenz.divide(BigInteger.valueOf(2)), modulu);
			return halbePotenz.multiply(halbePotenz).mod(modulu);
			
		}else{
			return basis.multiply(moduluPotenz(basis, potenz.subtract(BigInteger.valueOf(1)), modulu)).mod(modulu);
		}
			
	}
	
}
