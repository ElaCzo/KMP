import java.util.ArrayList;

public class KMP {

    public static int[] retenues(char[] facteur) {
        int[] res = new int[facteur.length+1];
        ArrayList<Character> lettres = new ArrayList<>();

        lettres.add(facteur[0]);

        int i = 0;
        res[0]=-1;
        int j = 0;

        for (i = 1; i < facteur.length; i++) {
            System.out.println("i="+i);
            if (!lettres.contains(facteur[i])) {
                lettres.add(facteur[i]);
                res[i] = 0;
            } else if (facteur[i] == facteur[0]) {
                lettres.add(facteur[i]);
                res[i] = -1;
                System.out.println("moins 1 a");
                for (j = 1; /*j < i && */(i + j) < facteur.length; j++) {
                    if (facteur[i + j] == facteur[j] && facteur[i + j] == facteur[0])
                        res[i + j] = -1;
                    else if (facteur[i + j] == facteur[j])
                        res[i + j] = 0;
                    else {
                        if (facteur[i + j] == facteur[0]) {
                            i = i + j - 1;
                            System.out.println("moins 1 b");
                            break;
                        } else {
                            res[i + j] = j;
                            i = i + j;
                            break;
                        }
                    }
                }

                System.out.println("arrivé.");
                i=i+j-1;
            }
            System.out.println("res["+(i-1)+"]="+res[i-1]);
        }
        res[res.length-1]=0;

        return res;
    }

    public static int match(char[] facteur, int[] retenue, char[] texte){
        int i=0; int j=0;

        while(i<texte.length){
            if(j==facteur.length){
                return (i-facteur.length);
            }
            if(texte[i]==facteur[j]){i++; j++;}
            else {
                if (retenue[j] == -1) {
                    i++;
                    j = 0;
                } else {
                    j = retenue[j];
                }
            }
        }
        if(j==facteur.length){return (i-facteur.length);} else return -1;
    }
    public static void main(String[] args){
        int[] res = retenues("mamamia".toCharArray());
        for(int i=0; i<res.length; i++)
            System.out.print(res[i]+" ");
        System.out.println();

        res = retenues("mami".toCharArray());
        for(int i=0; i<res.length; i++)
            System.out.print(res[i]+" ");
        System.out.println();
    }
}
