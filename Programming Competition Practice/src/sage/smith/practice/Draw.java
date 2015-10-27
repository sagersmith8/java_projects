package sage.smith.practice;

public class Draw {
    private char star = '*', space= ' ';
    public void drawN(int n){
        for(int i = 0; i < n; i++){
            System.out.print(star);
            for(int j = 1; j < n-1; j++){
                if(j==i){
                    System.out.print(star);
                }
                else{
                    System.out.print(space);
                }
            }
            System.out.print(star);
            System.out.println();
        }
    }

    public void drawY(int n){
        for(int i = 0; i < n-1; i++){
            for(int j = 0; j <= n+1; j++){
                if(j==i){
                    System.out.print(star);
                }

                if(i == 0 && j== n+1){
                    System.out.print(star);
                }
                else if(n+1-i == j){
                    System.out.print(star);
                }
                else{
                    System.out.print(space);
                }
            }
            System.out.println();
        }
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= (n/2)+1; j++){
                if((n/2)+1 == j){
                    System.out.print(star);
                }
                else{
                    System.out.print(space);
                }
            }
            System.out.println();
        }
    }

    public void drawDiamond(int n){
        for(int i = 0; i < n*2+1; i++){
            for(int j = 0; j < n+1; j++){
            }
        }
    }

    public static void main(String[] args) {
	    /*new Draw().drawN(5);
        System.out.println();
        System.out.println();
        System.out.println();
        new Draw().drawY(5);
        System.out.println();
        System.out.println();
        System.out.println();*/
        new Draw().drawDiamond(5);
    }
}
