package dados;

public class Operadores {
    public  static  boolean  in ( int  value , int [] arr ) {
        for ( int i =  0 ; i < arr . length; i ++ ) {
            if (arr [i] == value) {
                return  true ;
            }
        }
        return  false ;
    }
}
