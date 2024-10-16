import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PCConfig {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        
        int userChoice;
        int totalCost = 0;

        printBanner();

        System.out.print("How many PCs do you want to purchase? ");
        int amount = scan.nextInt();
        scan.nextLine();

        try {

            System.out.print("Enter the filename for your receipt: ");
            String path = scan.nextLine();
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(path))));

            System.out.println("");

            for (int i = 0; i < amount; i++) {
                System.out.println("PC #" + (i + 1));
                System.out.println("-----");

                String[] upgradeName = new String[10];
                int baseCost = 1750;
                int pcCost = 0;
                
                int count = 0;

                do {
                    upgradeMenu();
                    

                    System.out.print("Enter the number of your choice: ");
                    userChoice = scan.nextInt();
                    
                    if (userChoice < 5) {
                        hardwareChoice(userChoice);     // Show the hardware
                        int partUpgrade = scan.nextInt();
                        
                        if (userChoice == 1) {
                            pcCost += processorUpgrade(partUpgrade);
                            upgradeName[count] = "Processor: " + processorName(partUpgrade);
                        } else if (userChoice == 2) {
                            pcCost += graphicsCardUpgrade(partUpgrade);
                            upgradeName[count] = "Graphics card: " + graphicsCardName(partUpgrade);
                        } else if (userChoice == 3) {
                            pcCost += memoryUpgrade(partUpgrade);
                            upgradeName[count] = "Memory: " + memoryName(partUpgrade);
                        } else if (userChoice == 4) {
                            pcCost += monitorUpgrade(partUpgrade);
                            upgradeName[count] = "Monitor: " + monitorName(partUpgrade);
                        }

                        count += 1;
                        
                    }
                    System.out.println("");
                } while (userChoice != 5);
                
                pcCost += baseCost;
                System.out.printf("You have chosen these options for a PC with a total cost of $%.2f:",(double)pcCost);

                pw.printf("PC #%d ($%.2f):",i+1,(double)pcCost);
                pw.println("");
                System.out.println("\n");

                for (int j = 0; j < upgradeName.length; j++) {
                    if (upgradeName[j] != null) {
                        System.out.println(upgradeName[j]);
                        pw.println(upgradeName[j]);
                    }
                }
                pw.println("");
                System.out.println("");
                System.out.println("");
                totalCost += pcCost;
            }
            System.out.printf("The total cost of your order of %d PC(s) is $%.2f", amount, (double)totalCost);
            System.out.println("");
            System.out.println("Your order has been saved to " + path);
            System.out.println("Thank you for shopping with us");

            scan.close();
            pw.close();

        } catch (IOException Ex) {
        System.out.println("A file error occurred.");
        }
        
    }
    
    public static void printBanner() {
        System.out.println("******************************************************************");
        System.out.println("*                                                                *");
        System.out.println("*               Welcome to the PC Configuration                  *");
        System.out.println("*                                                                *");
        System.out.println("******************************************************************");
        System.out.println("");
    }

    public static void upgradeMenu() {
        System.out.println("What would you like to upgrade?");
        System.out.println("1. Processor");
        System.out.println("2. Graphics card");
        System.out.println("3. Memory");
        System.out.println("4. Monitor");
        System.out.println("5. Nothing else - I'm done.");
    }

    public static void hardwareChoice(int userChoice) {
        if (userChoice == 1) {
            System.out.print("Upgrade the processor to [1] Intel i7 ($200), [2] Intel i9 ($300), [3] AMD 9 5950 ($500): ");
        } else if (userChoice == 2) {
            System.out.print("Update the graphics card to [1] NVidia 3060 ($150), [2] NVidia 4060 ($250), [3] NVidia 4080 ($350): ");
        } else if (userChoice == 3) {
            System.out.print("Increase the memory to [1] 16GB, [2] 32GB: ");
        } else if (userChoice == 4) {
            System.out.print("Add a monitor of size [1] 24 inches ($200), [2] 27 inches ($250), [3] 32 inches ($350): ");
        }
    }

    public static int processorUpgrade(int userChoice) {
        switch (userChoice) {
            case 1:
                return 200;
            case 2:
                return 300;
            case 3:
                return 500;
        }
        return 0;
    }

    public static String processorName (int userChoice) {
        switch (userChoice) {
            case 1:
                return "Intel i7";
            case 2:
                return "Intel i9";
            case 3:
                return "AMD 9 5950";
        }
        return null;
    }

    public static int graphicsCardUpgrade(int userChoice) {
        switch (userChoice) {
            case 1:
                return 150;
            case 2:
                return 250;
            case 3:
                return 350;
        }
        return 0;
    }

    public static String graphicsCardName (int userChoice) {
        switch (userChoice) {
            case 1:
                return "NVidia 3060";
            case 2:
                return "NVidia 4060";
            case 3:
                return "NVidia 4080";
        }
        return null;
    }

    public static int memoryUpgrade(int userChoice) {
        switch (userChoice) {
            case 1:
                return 150;
            case 2:
                return 250;
        }
        return 0;
    }

    public static String memoryName (int userChoice) {
        switch (userChoice) {
            case 1:
                return "16GB";
            case 2:
                return "32GB";
        }
        return null;
    }

    public static int monitorUpgrade(int userChoice) {
        switch (userChoice) {
            case 1:
                return 200;
            case 2:
                return 250;
            case 3:
                return 350;
        }
        return 0;
    }

    public static String monitorName(int userChoice) {
        switch (userChoice) {
            case 1:
                return "24-inch";
            case 2:
                return "27-inch";
            case 3:
                return "32-inch";
        }
        return null;
    }
}



