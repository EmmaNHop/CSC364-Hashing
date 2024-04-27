import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static java.lang.Character.isUpperCase;

public class hash {
    int coll1 = 0;
    int coll2 = 0;
    public List<String>[] T1;
    public List<String>[] T2;
    int sum1;
    int sum2;

    public hash(int n) {
        T1 = new List[n];
        T2 = new List[n];
        for(int i = 0; i < n; i++){
            T1[i] = new LinkedList<>();
            T2[i] = new LinkedList<>();
        }
    }
    public void insertHash1(String word) {
        //hash 1 input
        int h1 = hash1(word);
        if (T1[h1].contains(word)) {
            return; //bypass collision, in list already
        }
        if (!T1[h1].isEmpty()) {
            coll1++;
        }
        T1[h1].add(0, word);
    }
    public void insertHash2(String word){
        //hash 2 input

        int h2 = hash2(word);
        if(T2[h2].contains(word)){
            return;
        }
        if (!T2[h2].isEmpty()){
            coll2++;
        }
        T2[h2].add(0, word);
    }

    public int hash1(String word){
        sum1 = 0;

        for(int i = 0; i < word.length(); i++){
            sum1 = sum1 + word.charAt(i);
        }
        return sum1;// % 59;
    }
    public int hash2(String word){
        sum2 = 0;

        //Bit 0 - set to 1 if the string is an odd length or 0 if it is even length
        if(word.length() % 2 != 0){
            sum2 += 1;
        }

        //Bit 1 - set to 1 if the string begins with a vowel – uppercase or lowercase (a,e,I,o,u,A,E,I,O,U) or a 0 if it begins with any non-vowel symbol.
        char c1 = word.charAt(0);
        if(c1 == 'a' || c1 == 'A' || c1 == 'e' || c1 == 'E' || c1 == 'i' || c1 == 'I' || c1 == 'o' || c1 == 'O' || c1 == 'u' || c1 == 'U'){
            sum2 += 2;
        }

        //Bit 2 - set to 1 if the sum of all ASCII values of symbols in the string is odd or 0 if the sum is even.
        int ascii = 0;
        for(int i = 0; i< word.length(); i++)
            ascii  += word.charAt(i);
        if(ascii % 2 != 0)
            sum2 += 4;

        //Bit 3 - set to 1 if any symbol in the string is repeated or 0 if it is not.
        ArrayList<Character> set = new ArrayList<>();
        boolean repeat = false;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(set.contains(c)){
                repeat = true;
                break;
            }
            set.add(c);
        }
        if(repeat){
            sum2 += 8;
        }

        //Bit 4 - set to 1 if the sum of the first + middle + last symbol of the string is odd or 0 if it is even.
        int firstA = word.charAt(0);
        int midA = word.charAt((word.length() - 1) / 2);
        int lastA = word.charAt(word.length() - 1);
        if((firstA + midA + lastA) % 2 != 0){
            sum2 += 16;
        }

        //Bit 5 - set to 1 if the string contains an uppercase symbol (A-Z) or a 0 if it does not.
        boolean upper = false;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (isUpperCase(c)) {
                upper = true;
                break;
            }
        }
        if(upper){
            sum2 += 32;
        }

        //Bit 6 - set to 1 if the string, when read from left to right, is lexicographically less than the string when read from right to left or 0 if it is not.
        String rev = "";
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            rev = c + rev;
        }
        if(word.compareTo(rev) < 0){
            sum2 += 64;
        }

        //Bit 7 – the high order bit should be set to 1 if the string is 6 letters or less in length or 0 otherwise.
        if(word.length() <= 6){
            sum2 += 128;
        }

        return sum2 % 59;
    }
    public int getSum1(){
        return sum1;
    }
    public int getSum2(){
        return sum2;
    }
    public int getColl1(){
        return coll1;
    }
    public int getColl2(){
        return coll2;
    }
}