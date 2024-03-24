import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SineStarCanteen {

    public static void main(String[] args) {
        // Operations.....Performed.........By........Canteen........In...........DAIICT  :><:
        Scanner scan = new Scanner(System.in);

        List<UserListData> l = new ArrayList<UserListData>();//list for user

        AddItemsinCanteen.addITEMS();//Add items first in canteen

        while (true) {
            System.out.println("Enter user ,  what they want : ");
            System.out.println("1------List All items");
            System.out.println("2------take order");
            System.out.println("3------cancel order by user");
            System.out.println("4------List item user order in current state");
            System.out.println("5------DONE exit user");
            int chs = scan.nextInt();
            switch (chs) {
                case 1:
                    AddItemsinCanteen.AllitemsInCanteenAvL();
                    break;
                case 2:
                    System.out.println("Enter id : ");
                    int id = scan.nextInt();
                    System.out.println("Enter Qty : ");
                    int qty = scan.nextInt();
                    AddItemsinCanteen.takeOrderFunction(l,id,qty);
                    break;
                case 3:
                    System.out.println("Enter id : ");
                    int id1 = scan.nextInt();
                    System.out.println("How many Qty remove in list : ");
                    int qty1 = scan.nextInt();
                    AddItemsinCanteen.cancelOrderFunction(l,id1,qty1);
                    break;
                case 4:
                    AddItemsinCanteen.OrderListUser(l);
                    break;
                case 5:
                    System.out.println("Exit this program");
                    return;

                default:
                    System.out.println("Invalid Number");
                    break;
            }
        }
        
    }
    

    

}