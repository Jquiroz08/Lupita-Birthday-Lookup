package com.birthday;

import java.io.*;
import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class BirthdayExample {

  // this is a private and static hashmap to store the birthdays
  private static HashMap<String, String> birthdayMap = new HashMap<String, String>();

  // this code reads a the json file
  // students do not have to change this function
  public static JSONArray readJSONArrayFile(String fileName) {
    // JSON parser object to parse read file
    JSONParser jsonParser = new JSONParser();

    JSONArray birthdayArr = null;

    // Read JSON file
    try (FileReader reader = new FileReader(fileName)) {
      Object obj = jsonParser.parse(reader);

      birthdayArr = (JSONArray) obj;
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return birthdayArr;
  }

  // students do not have to change this function
  public static void initializeMap(final String pathToFile) {
    JSONArray jsonData = readJSONArrayFile(pathToFile);

    // loop over list
    String birthday, name;
    JSONObject obj;
    for (Integer i = 0; i < jsonData.size(); i++) {
      // parse the object and pull out the name and birthday
      obj = (JSONObject) jsonData.get(i);
      birthday = (String) obj.get("birthday");
      name = (String) obj.get("name");

      // add the name and birthday in to a hashmap
      birthdayMap.put(name, birthday);

      // print the names and birthdays
      //System.out.println("name = " + name);
      //System.out.println("birthday = " + birthday);
    }
  }

  public static void main(final String[] args) {
    /*
     * students will need to change the path below to work on THEIR laptop. this is currently the path for my laptop.
     * if students do not know or understand what a "path" is, students should first complete the
     * extra credit module on Files, Directories, and Folders in Canvas.
     */
    String pathToFile =
      "C:\\Users\\josep\\OneDrive\\Desktop\\Git Repo\\Lupita-Birthday-Lookup\\src\\birthday_lookup\\target\\classes\\com\\birthday\\birthday.json";

    // students should change the code below in order to implment their own solution

    // initialize the hash map
    initializeMap(pathToFile);

    // read user input from keyboard
    System.out.println("Reading user input into a string");

    // get user input
    Scanner input = new Scanner(System.in);
    System.out.print("Enter a name:");
    String name = input.nextLine();

    //Boolean to check wether or not to print missing student statement
    Boolean isStudent = false;

    // print user input
    /* For eacn loop going through all the contents of the hashmap.
     * Checks if the user input is a subset of the name in the hashmap.
     * Prints the name and birthday if True.
     */
    for (String studentName : birthdayMap.keySet()) {
      if(studentName.contains(name)){
        System.out.println(studentName + "'s birthday is " + birthdayMap.get(studentName));
        isStudent = true;
      }
    }
    //Prints statement if student not in database
    if(isStudent == false)
      System.out.println("Student is not in database");

    // close the scanner
    input.close();
  }
}
