import java.util.Random;
import java.math.BigInteger;
import java.util.*;
import java.io.*;


// privateKey = createNewFile(/home/lauteu34/rsa.schluessel/PrivKey.txt)
// publicKey = createNewFile(/home/lauteu34/rsa.schluessel/PubKey.txt)

import java.io.File;
import java.io.IOException;

public class xy {

    private boolean checkFile(File file) {
    		file.createNewFile();
    				
//    				"/home/lauteu34/rsa.schluessel/PubKey.txt");
            } catch (IOException e) {
                System.err.println("Error creating " + file.toString());
            }
            if (file.isFile() && file.canWrite() && file.canRead())
                return true;
        }
        return false;
    }
    public static void main(String[] args) {
        DateiErzeugen da = new DateiErzeugen();
        String dat = "PubKey.txt";
        if(da.checkFile(new File(dat)))
            System.out.println(dat);
    }
} 