import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Author: Yutong Wu(yw6228)
 * Email: yw6228@rit.edu
 * Description: This file solves the stable matching problem.
 */

public class Stable {
    /**
     * This Method reads an input file and put the information disregarding spaces in the indicated output file
     * It also builds lists of men and women for stable matching
     * @param input The input file
     * @param output The output file
     * @param men List of Men
     * @param women List of women
     */
    public static void readFile(String input, String output,ArrayList<Person> men, ArrayList<Person> women){
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            br = new BufferedReader(new FileReader(input));
            FileWriter out= new FileWriter(output, false);
            bw = new BufferedWriter(out);
            int lineNum = 0;
            int numP = 0;
            String line = br.readLine();
            while(line != null){
                if(lineNum == 0){
                    numP = Integer.parseInt(line);
                    bw.write(numP + "\n");
                }else{
                    if(line.equals("")){
                        line = br.readLine();
                        continue;
                    }
                    String contents[] = line.split(" ");
                    Person p = new Person();
                    int currRank = 0; //Reports the current rank of the man/woman, so when there is an extra white
                    //space, it does not mess up the number.
                    for(int i = 0; i < contents.length; i++){
                        if(contents[i].equals("") || contents[i].equals("\t")){
                            continue;
                        }
                        if(currRank == 0){
                            p.changeName(contents[i]);
                        }else{
                            int pref = Integer.parseInt(contents[i]);
                            p.addPreference(pref);
                        }
                        currRank ++;
                        if(i == contents.length - 1){
                            bw.write(contents[i] + "\n");
                        }else{
                            bw.write(contents[i] + " ");
                        }
                    }
                    if(lineNum <= numP){
                        men.add(lineNum,p);
                    }else{
                        women.add(lineNum - numP, p);
                    }

                }
                line = br.readLine();
                lineNum ++;
            }
            br.close();
            bw.close();
        } catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    /**
     * This method performs the stable matching algorithm. It also outputs the matching result to
     * the output file provided
     * @param output Output file
     * @param men list of men
     * @param women list of women
     */
    public static void match(String output, ArrayList<Person> men, ArrayList<Person> women){
        BufferedWriter bw = null;
        try{
            FileWriter out = new FileWriter(output,true);
            bw = new BufferedWriter(out);
            Queue<Integer> wl = new LinkedList<>();
            for(int i = 1; i < men.size(); i++){
                wl.add(i);
            }
            /**
             * Stable matching algorithm
             */
            while(!wl.isEmpty()){
                int manNum = wl.poll();
                Person p = men.get(manNum);
                for(int womanRank = 0; womanRank < p.getPreference().size(); womanRank++){
                    if(!p.checkFree()){
                        //If man is engaged, immediately break
                        break;
                    }
                    Person w = women.get(p.getPreference().get(womanRank));
                    if(w.checkFree()){
                        //If woman is free, immediately engage
                        w.setMatch(manNum);
                        p.setMatch(p.getPreference().get(womanRank));
                        break;
                    }else{
                        ArrayList<Integer> wPref = w.getPreference();
                        int currentMatch = w.getMatch();
                        for(int man : wPref){
                            if(man == currentMatch){
                                //If woman's current match's rank is higher than the current man, ignore this woman
                                break;
                            }else if(man == manNum){
                                //If woman's current match's rank is lower than the current man, engage this
                                //woman with the current man, and set the prior match free.
                                w.setMatch(manNum);
                                p.setMatch(p.getPreference().get(womanRank));
                                men.get(currentMatch).setFree();
                                wl.add(currentMatch);
                                break;
                            }
                        }
                    }
                }
            }
            /**
             * Put matching result in output
             */
            for(int i = 1; i < men.size(); i++){
                bw.write(men.get(i).getName() + " w" + men.get(i).getMatch());
                if(i != men.size() - 1){
                    bw.write("\n");
                }
            }
            bw.close();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

    }

    /**
     * This is the main function of the program
     */
    public static void main(String[] args){
        if(args.length < 2){
            System.out.println("Usage: Stable input output");
        }else{
            String inputName = args[0];
            String outputName = args[1];
            ArrayList<Person> men = new ArrayList<>();
            men.add(new Person());//place holder, no m0
            ArrayList<Person> women = new ArrayList<>();
            women.add(new Person());//place holder, no w0
            readFile(inputName, outputName, men, women);
            match(outputName, men, women);
        }

    }
}
