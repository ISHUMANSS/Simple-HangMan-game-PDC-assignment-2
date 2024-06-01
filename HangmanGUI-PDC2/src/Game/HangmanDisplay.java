package Game;


public class HangmanDisplay{
    
    private int count;
    
    public HangmanDisplay(){
        this.count = 0;
    }

    public void displayHangman(){
        switch (count){

            case 1:
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("_______");
                System.out.println();
            break;

            case 2:
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("   |");
                System.out.println("_______");
            break;

            case 3:
                System.out.println("  ________");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("  |");
                System.out.println("______");
            break;

            case 4:
                System.out.println("  __________");
                System.out.println("  |        |");
                System.out.println("  |        O");
                System.out.println("  |       ");
                System.out.println("  |        ");
                System.out.println("  |        ");
                System.out.println("  |        ");
                System.out.println("______");
            break;

            case 5:
                System.out.println("  __________");
                System.out.println("  |        |");
                System.out.println("  |        O");
                System.out.println("  |       \\");
                System.out.println("  |        ");
                System.out.println("  |        ");
                System.out.println("  |        ");
                System.out.println("______");
            break;

            case 6:
                System.out.println("  __________");
                System.out.println("  |        |");
                System.out.println("  |        O");
                System.out.println("  |       \\|/");
                System.out.println("  |        ");
                System.out.println("  |        ");
                System.out.println("  |        ");
                System.out.println("______");
            break;


            case 7:
                System.out.println("  __________");
                System.out.println("  |        |");
                System.out.println("  |        O");
                System.out.println("  |       \\|/");
                System.out.println("  |        |");
                System.out.println("  |        |");
                System.out.println("  |       / ");
                System.out.println("______");
            break;

            case 8:
                System.out.println("   DEFEAT!!");
                System.out.println("  __________");
                System.out.println("  |        |");
                System.out.println("  |        O");
                System.out.println("  |       \\|/");
                System.out.println("  |        |");
                System.out.println("  |        |");
                System.out.println("  |       / \\");
                System.out.println("______");
                //this.makeCount0();
            break;
            default:
            break;
            }
    }

    public void incrementCount(){
        count++;
    }

    public int obtainCount(){
        return count;
    }
    
    public void makeCount0(){
        count = 0;
    }
}
