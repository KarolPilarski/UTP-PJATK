/**
 *
 *  @author Pilarski Karol S22682
 *
 */

package zad1;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Finder {

    char[] charArray;


    public Finder(String path) throws FileNotFoundException {
        String str = "";

        Scanner scanner = new Scanner(new File(path));

        str = scanner.nextLine();
        while (scanner.hasNextLine()) {
            str = str + "\n" + scanner.nextLine();
        }

        charArray = str.toCharArray();
    }

    public int getIfCount() {
        int count=0;
        Boolean quote = false;
        for(int i=0;i<charArray.length-3;i++){
            if(charArray[i]=='"'){
                if(quote) quote=false;
                else quote=true;
            }
            if((charArray[i]=='I'||charArray[i]=='i')&&(charArray[i+1]=='f'||charArray[i+1]=='F')&&((charArray[i+2]=='(')||((charArray[i+2]==' ')&&(charArray[i+3]=='(')))){
                if(!quote)count++;
            }
        }
        return count;
    }

    public int getStringCount(String str) {
        int count=0;
        char[] strToChar = str.toCharArray();
        for(int i=0;i<=charArray.length-strToChar.length;i++){
            if(charArray[i]==strToChar[0]){
                boolean matches = true;
                for(int j=1;j<strToChar.length;j++){
                    if(strToChar[j]!=charArray[i+j]) matches=false;
                }
                if(matches)count++;
            }
        }
        return count;
    }
}
