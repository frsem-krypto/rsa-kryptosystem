import java.util.Random;
import java.math.BigInteger;
import java.util.*;
import java.io.*;


// privateKey = createNewFile(/home/lauteu34/rsa.schluessel/PrivKey.txt)
// publicKey = createNewFile(/home/lauteu34/rsa.schluessel/PubKey.txt)

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class xy {

//    private boolean checkFile(File file) {
//    		try{
//    		file.createNewFile();
//    				
////    				"/home/lauteu34/rsa.schluessel/PubKey.txt");
//            } catch (IOException e) {
//                System.err.println("Error creating " + file.toString());
//            }
//            if (file.isFile() && file.canWrite() && file.canRead())
//                return true;
//        
//        return false;
//    }
    public static void main(String[] args) {

    	switch(args[0]){
    	case "decrypt":{
    		File message = new File(args[1]);
			InputStreamReader isr;
			ArrayList<String> strings = new ArrayList<String>();

			try {
				isr = new InputStreamReader(new FileInputStream(message));
				BufferedReader br = new BufferedReader(isr);
				while(true){
					String line = br.readLine();
					if(line == null){
						break;
					}
					strings.add(line);
				}
			    br.close();
				
			} catch (FileNotFoundException e) {
				System.err.println("Fehler beim Einlesen der Nachricht: Nachricht nicht gefunden");
				return;
			} catch (IOException e) {
				System.err.println("Fehler beim Einlesen der Nachricht");
			}
			
    		if(!args[2].endsWith(".rsapriv")){
    			System.out.println("Bitte geben Sie als zweites Argument Ihren privaten (.rsapriv) Schluessel an");
    			return;
    		}
    		File file = new File(args[2]);
    		rsa.Key keypriv = new rsa.Key(file);
    		
    		
    		for(String str : strings){
    			BigInteger m = new BigInteger(str.getBytes());
    			BigInteger decrypted = rsa.dech(m, keypriv);
    			System.out.print(decrypted);
    		}
    	}break;
    	case "encrypt":
    		File message = new File(args[1]);
			InputStreamReader isr;
			StringBuffer strings = new StringBuffer();

			try {
				isr = new InputStreamReader(new FileInputStream(message));
				BufferedReader br = new BufferedReader(isr);
				while(true){
					String line = br.readLine();
					if(line == null){
						break;
					}
					strings.append(line);
					strings.append("/n");
				}
			    br.close();
				
			} catch (FileNotFoundException e) {
				System.err.println("Fehler beim Einlesen der Nachricht: Nachricht nicht gefunden");
				return;
			} catch (IOException e) {
				System.err.println("Fehler beim Einlesen der Nachricht");
			}
			
    		if(!args[2].endsWith(".rsapub")){
    			System.out.println("Bitte geben Sie als zweites Argument einen oeffentlichen (.rsapub) Schluessel an");
    			return;
    		}
    		File file = new File(args[2]);
    		rsa.Key keypub = new rsa.Key(file);
    		
    		int bytesNumber = keypub.getN().bitLength() / 8;
    		byte[] bytes = strings.toString().getBytes();
    		
    		int b = 0;
    		StringBuilder sb = new StringBuilder();
    		while(true){
    			byte[] currentBytes = new byte[bytesNumber];

    			for(int i = 0; i < bytesNumber; i++){
    				if(b < bytes.length){
    					currentBytes[i] = bytes[b];
    					b++;
    				}else{
    					break;
    				}

    			}
				BigInteger m = new BigInteger(currentBytes);
				BigInteger en = rsa.chif(m, keypub);
				sb.append(new String(en.toByteArray()));
				if(b >= bytes.length){
					break;
				}
    		}
    		String userHome = System.getProperty("user.home");
    		File chifm = new File("user.Home");
    		
//    		break;
    	case "verify":
    		
    		
    		break;
    	case "sign":
    		
    		break;
    	case "createnewkey":
    		
    	}
    	
    }
} 