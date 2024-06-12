import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SineStarCanteen {

    public static void main(String[] args) {

        System.out.println("// -----------------------------------------------------//");
        System.out.println("//                .....Operations.......               //");
        System.out.println("//                ......Performed.......               //");
        System.out.println("//                .........By...........               //");
        System.out.println("//                .......Canteen........               //");
        System.out.println("//                .........at...........               //");
        System.out.println("//                .......DAIICT.........               //");
        System.out.println("// -----------------------------------------------------//");

        Scanner scan = new Scanner(System.in);

        List<UserListData> l = new ArrayList<UserListData>();// list for user

        while (true) {
            System.out.println("1 - Owner");
            System.out.println("2 - Customer");
            System.out.println("3 - ExitProgram");
            System.out.println("Please select your role: ");
            int ch = scan.nextInt();
            if (ch == 3) {
                System.out.println("Exit DAIICT canteen");
                break;
            }
            switch (ch) {
                case 1:
                    while (true) {
                        System.out.println("1 - Add Items");
                        System.out.println("2 - View All Items");
                        System.out.println("3 - Update Item Price");
                        System.out.println("4 - Exit Owner Menu");
                        System.out.println("Please select an option: ");
                        int choise = scan.nextInt();
                        if (choise == 4) {
                            System.out.println("Exiting the Owner menu...");
                            break;
                        }
                        switch (choise) {
                            case 1:
                                AddItemsinCanteen.addITEMS();
                                break;
                            case 2:
                                System.out.println("List of all available items in the canteen:");
                                AddItemsinCanteen.AllitemsInCanteenAvL();
                                break;
                            case 3:// update the item price
                                System.out.println("Enter item ID to update: ");
                                int id = scan.nextInt();
                                System.out.println("Enter new price: ");
                                int price = scan.nextInt();
                                AddItemsinCanteen.updatePrice(id, price);
                                break;
                            default:
                                System.out.println("Invalid selection. Please try again.");
                                break;
                        }

                    }
                    break;
                case 2:

                    while (true) {
                        System.out.println("1 - View All Items");
                        System.out.println("2 - Place Order");
                        System.out.println("3 - Cancel Order");
                        System.out.println("4 - View Current Order");
                        System.out.println("5 - Exit Customer Menu");
                        System.out.println("Please select an option: ");
                        int choise = scan.nextInt();
                        if (choise == 5) {
                            System.out.println("Exiting the customer menu...");
                            break;
                        }
                        switch (choise) {
                            case 1:
                                AddItemsinCanteen.AllitemsInCanteenAvL();
                                break;
                            case 2:
                                System.out.println("Enter item ID to order: ");
                                int id = scan.nextInt();
                                System.out.println("Enter quantity: ");
                                int qty = scan.nextInt();
                                AddItemsinCanteen.takeOrderFunction(l, id, qty);
                                break;
                            case 3:
                                System.out.println("Enter item ID to cancel: ");
                                int id1 = scan.nextInt();
                                System.out.println("Enter quantity to remove: ");
                                int qty1 = scan.nextInt();
                                AddItemsinCanteen.cancelOrderFunction(l, id1, qty1);
                                break;
                            case 4:
                                AddItemsinCanteen.OrderListUser(l);
                                break;
                            default:
                                System.out.println("Invalid selection. Please try again.");
                                break;
                        }
                    }

                default:
                    System.out.println("Invalid selection. Please try again.");
                    break;
            }
        }

    }
}
