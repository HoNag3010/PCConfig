/**
 * Welcome to PCConfig
 * @author Huy Hoang Nguyen
 * Import needed util
 */
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PCConfig {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in); // Make a scanner
        
        int userChoice;  // Declare userChoice to store user input
        int totalCost = 0;  // Declare totalCost to calculate of cost of all PC

        printBanner();     // Print the welcome banner

        System.out.print("How many PCs do you want to purchase? ");
        int amount = scan.nextInt();        // This integer used for loop time
        scan.nextLine();

        try {       // This to catch file error

            System.out.print("Enter the filename for your receipt: ");
            String path = scan.nextLine();      // The path user chose to print the text file
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(new File(path))));       // I don't even know what is this but it
                                                                                                        // writes my code in the text file
            System.out.println("");

            for (int i = 0; i < amount; i++) {      // For loop to loop the coded based on number of PC provided
                System.out.println("PC #" + (i + 1));
                System.out.println("-----");        // Print the PC number on top of every loop

                String[] upgradeName = new String[10];      // Make an array to store for later printing
                int baseCost = 1750;        // Base cost of a PC
                int pcCost = 0;     // The cost that is used to calculate
                int count = 0;      // This is keep track of for correct storing

                do {        // Do.. while loop so the code at least run 1 time
                    upgradeMenu();      // Print the upgrade options
                    

                    System.out.print("Enter the number of your choice: ");
                    userChoice = scan.nextInt();        // User input
                    
                    if (userChoice < 5) {   // Check if the user wants to end
                        hardwareChoice(userChoice);     // Show the hardware
                        int partUpgrade = scan.nextInt();
                        
                        if (userChoice == 1) {      // This is used to check what component and what choices
                            pcCost += processorUpgrade(partUpgrade);    // Adding the money to the pcCost
                            upgradeName[count] = "Processor: " + processorName(partUpgrade);        // Store the printing to an array
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

                        count += 1;     // Increment the count every loop
                        
                    }
                    System.out.println("");
                } while (userChoice != 5);      
                
                pcCost += baseCost;     // Add the final cost
                System.out.printf("You have chosen these options for a PC with a total cost of $%.2f:",(double)pcCost);     // Print the concluding message
                System.out.println("\n");

                pw.printf("PC #%d ($%.2f):",i+1,(double)pcCost);        // Write the PC number and total cost of that PC to a file
                pw.println("");

                for (int j = 0; j < upgradeName.length; j++) {      // For loop to print everything out of an array based on its length
                    if (upgradeName[j] != null) {
                        System.out.println(upgradeName[j]);     // Print everything out
                        pw.println(upgradeName[j]);     // Write everything into the file
                    }
                }
                pw.println("");
                System.out.println("");
                System.out.println("");
                totalCost += pcCost;        // Add the done PC's cost to total cost
            }
            System.out.printf("The total cost of your order of %d PC(s) is $%.2f", amount, (double)totalCost);      // Print the total cost
            System.out.println("");
            System.out.println("Your order has been saved to " + path);     // Print the selected path
            System.out.println("Thank you for shopping with us");       // Thank you message

            scan.close();
            pw.close();

        } catch (IOException Ex) {
        System.out.println("A file error occurred.");
        }
        
    }
    
    /**
     * This method prints a welcome banner
     */
    public static void printBanner() {
        System.out.println("******************************************************************");
        System.out.println("*                                                                *");
        System.out.println("*               Welcome to the PC Configuration                  *");
        System.out.println("*                                                                *");
        System.out.println("******************************************************************");
        System.out.println("");
    }

    /**
     * This method prints options for choosing
     */
    public static void upgradeMenu() {
        System.out.println("What would you like to upgrade?");
        System.out.println("1. Processor");
        System.out.println("2. Graphics card");
        System.out.println("3. Memory");
        System.out.println("4. Monitor");
        System.out.println("5. Nothing else - I'm done.");
    }

    /**
     * This method prints different options based on user chosen component
     * @param userChoice is which component they chose
     */
    public static void hardwareChoice(int userChoice) {
        if (userChoice == 1) {
            System.out.print("Upgrade the processor to [1] Intel i7 ($200), [2] Intel i9 ($300), [3] AMD Ryzen 9 5950 ($500): ");
        } else if (userChoice == 2) {
            System.out.print("Update the graphics card to [1] NVidia 3060 ($150), [2] NVidia 4060 ($250), [3] NVidia 4080 ($350): ");
        } else if (userChoice == 3) {
            System.out.print("Increase the memory to [1] 16GB, [2] 32GB: ");
        } else if (userChoice == 4) {
            System.out.print("Add a monitor of size [1] 24 inches ($200), [2] 27 inches ($250), [3] 32 inches ($350): ");
        }
    }

    /**
     * If processor is chosen, this method will return based on choice
     * @param userChoice is what type of processor did user choose
     * @return the money the component costs
     */
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

    /**
     * This bases on user choice to print the correct string
     * @param userChoice userchoice
     * @return name of the selected component
     */
    public static String processorName (int userChoice) {
        switch (userChoice) {
            case 1:
                return "Intel i7";
            case 2:
                return "Intel i9";
            case 3:
                return "AMD 9 Ryzen 5950";
        }
        return null;
    }

    /**
     * If graphics card is chosen, this method will return based on choice
     * @param userChoice is what type of graphics card did user choose
     * @return the money the component costs
     */
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

    /**
     * This bases on user choice to print the correct string
     * @param userChoice userchoice
     * @return name of the selected component
     */
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

    /**
     * If memory is chosen, this method will return based on choice
     * @param userChoice is what type of memory did user choose
     * @return the money the component costs
     */
    public static int memoryUpgrade(int userChoice) {
        switch (userChoice) {
            case 1:
                return 150;
            case 2:
                return 250;
        }
        return 0;
    }

    /**
     * This bases on user choice to print the correct string
     * @param userChoice userchoice
     * @return name of the selected component
     */
    public static String memoryName (int userChoice) {
        switch (userChoice) {
            case 1:
                return "16GB";
            case 2:
                return "32GB";
        }
        return null;
    }

    /**
     * If monitor is chosen, this method will return based on choice
     * @param userChoice is what type of monitor did user choose
     * @return the money the component costs
     */
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

    /**
     * This bases on user choice to print the correct string
     * @param userChoice userchoice
     * @return name of the selected component
     */
    public static String monitorName(int userChoice) {
        switch (userChoice) {
            case 1:
                return "24 inch";
            case 2:
                return "27 inch";
            case 3:
                return "32 inch";
        }
        return null;
    }
}



