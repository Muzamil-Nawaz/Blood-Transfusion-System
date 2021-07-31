
/**
 * @(#)Assignment04.java
 *
 *
 * @author
 * @version 1.00 2020/4/9
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;

public class Assignment04 {

    static ArrayList<Patient> recipients, list;
    static boolean[][] bloodTypesMatches;
    static HashMap<String, Integer> bloodTypes;
    public static void main(String[] args) throws IOException {
        bloodTypes = new HashMap();
        bloodTypes.put("O-", bloodTypes.size() + 1);
        bloodTypes.put("O+", bloodTypes.size() + 1);
        bloodTypes.put("A-", bloodTypes.size() + 1);
        bloodTypes.put("A+", bloodTypes.size() + 1);
        bloodTypes.put("B-", bloodTypes.size() + 1);
        bloodTypes.put("B+", bloodTypes.size() + 1);
        bloodTypes.put("AB-", bloodTypes.size() + 1);
        bloodTypes.put("AB+", bloodTypes.size() + 1);
        System.out.println(bloodTypes);

        bloodTypesMatches = new boolean[8][8];
        bloodTypesMatches[0][0] = true;
        bloodTypesMatches[1][0] = true;
        bloodTypesMatches[2][0] = true;
        bloodTypesMatches[3][0] = true;
        bloodTypesMatches[4][0] = true;
        bloodTypesMatches[5][0] = true;
        bloodTypesMatches[6][0] = true;
        bloodTypesMatches[7][0] = true;

        bloodTypesMatches[0][7] = false;
        bloodTypesMatches[1][7] = false;
        bloodTypesMatches[2][7] = false;
        bloodTypesMatches[3][7] = false;
        bloodTypesMatches[4][7] = false;
        bloodTypesMatches[5][7] = false;
        bloodTypesMatches[6][7] = false;
        bloodTypesMatches[7][7] = true;

        bloodTypesMatches[0][6] = false;
        bloodTypesMatches[1][6] = false;
        bloodTypesMatches[2][6] = false;
        bloodTypesMatches[3][6] = false;
        bloodTypesMatches[4][6] = false;
        bloodTypesMatches[5][6] = false;
        bloodTypesMatches[6][6] = true;
        bloodTypesMatches[7][6] = true;

        bloodTypesMatches[0][5] = false;
        bloodTypesMatches[1][5] = false;
        bloodTypesMatches[2][5] = false;
        bloodTypesMatches[3][5] = false;
        bloodTypesMatches[4][5] = false;
        bloodTypesMatches[5][5] = true;
        bloodTypesMatches[6][5] = false;
        bloodTypesMatches[7][5] = true;

        bloodTypesMatches[0][4] = false;
        bloodTypesMatches[1][4] = false;
        bloodTypesMatches[2][4] = false;
        bloodTypesMatches[3][4] = false;
        bloodTypesMatches[4][4] = true;
        bloodTypesMatches[5][4] = true;
        bloodTypesMatches[6][4] = true;
        bloodTypesMatches[7][4] = true;

        bloodTypesMatches[0][3] = false;
        bloodTypesMatches[1][3] = false;
        bloodTypesMatches[2][3] = false;
        bloodTypesMatches[3][3] = true;
        bloodTypesMatches[4][3] = false;
        bloodTypesMatches[5][3] = false;
        bloodTypesMatches[6][3] = false;
        bloodTypesMatches[7][3] = true;

        bloodTypesMatches[0][2] = false;
        bloodTypesMatches[1][2] = false;
        bloodTypesMatches[2][2] = true;
        bloodTypesMatches[3][2] = true;
        bloodTypesMatches[4][2] = false;
        bloodTypesMatches[5][2] = false;
        bloodTypesMatches[6][2] = true;
        bloodTypesMatches[7][2] = true;

        bloodTypesMatches[0][1] = false;
        bloodTypesMatches[1][1] = true;
        bloodTypesMatches[2][1] = false;
        bloodTypesMatches[3][1] = true;
        bloodTypesMatches[4][1] = false;
        bloodTypesMatches[5][1] = true;
        bloodTypesMatches[6][1] = false;
        bloodTypesMatches[7][1] = true;

        list = new ArrayList<Patient>();
        try {
            File file = new File("donors.txt");
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] arr = line.split(";");
                if (arr[1].equals("O-") || arr[1].equals("O+") || arr[1].equals("A-") || arr[1].equals("A+") || arr[1].equals("B-") || arr[1].equals("B+") || arr[1].equals("AB-") || arr[1].equals("AB+")) {
                    Patient p = new Patient(arr[0], arr[1]);
                    list.add(p);
                } else {
                    System.out.println(arr[0] + " has a problem with blood type.");
                }
            }
            System.out.println(list);
        } catch (Exception e) {
            System.out.println("There is a problem with you donors file");
        }

        try {
            recipients = new ArrayList<Patient>();
            File file2 = new File("recipients.txt");
            Scanner sc = new Scanner(file2);
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] arr = line.split(";");
                if (arr[1].equals("O-") || arr[1].equals("O+") || arr[1].equals("A-") || arr[1].equals("A+") || arr[1].equals("B-") || arr[1].equals("B+") || arr[1].equals("AB-") || arr[1].equals("AB+")) {
                    Patient p = new Patient(arr[0], arr[1]);
                    recipients.add(p);
                } else {
                    System.out.println(arr[0] + " has a problem with blood type.");
                }
            }
            System.out.println(recipients);
        } catch (Exception e) {
            System.out.println("There is a problem with your recipients file.");
        }

        ArrayList<ArrayList<Patient>> recipientMatches = new ArrayList<>();
        for (int i = 0; i < recipients.size(); i++) {
            ArrayList<Patient> donors = new ArrayList();
            for (int j = 0; j < list.size(); j++) {
                if (bloodTypesMatches[bloodTypes.get(recipients.get(i).getBloodType()) - 1][bloodTypes.get(list.get(j).getBloodType()) - 1]) {
                    donors.add(list.get(j));
                }
            }
            recipientMatches.add(donors);
        }
        ArrayList matchedDonors = new ArrayList();
        ArrayList matchedRecipients = new ArrayList();
        ArrayList<Patient> list2 = list;
        String output = "";
        for (int i = 0; i < recipients.size(); i++) {
            for (int j = 0; j < recipientMatches.get(i).size(); j++) {
                Patient p = list2.get(getPotentialDonor(recipients.get(i).getBloodType()));
                    output+=("Recipient: " + recipients.get(i).getName() + ";   " + recipients.get(i).getBloodType() + ";" + "   Donor: " + p.getName()+";   Round: 1st Round");
                    recipients.remove(j);
                    list2.remove(getPotentialDonor(recipients.get(i).getBloodType()));
                    break;
                
                
            }
        }
        FileWriter myWriter = new FileWriter(new File("Appointments.txt"));
        myWriter.write(output);
        myWriter.close();
    }
    public static int getPotentialDonor(String type){
        int count = 0,min=100,index=-1;
        if(type.equals("O-")){
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getBloodType().equals("O-"))
                    return i;
            }
        }
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < bloodTypesMatches.length; j++) {
                if(bloodTypesMatches[j][bloodTypes.get(list.get(i).getBloodType())-1])
                    count++;
            }
            if(count < min){
                min = count;
                index = i;
            }
            count=0;
        }
        return index;
    }

}
