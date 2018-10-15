import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    private ArrayList<Integer> stack = new ArrayList<Integer>();
    private ArrayList<Integer> sortedStack = new ArrayList<Integer>();
    private String filename;
    private boolean print;
    private int flipCount = 0;
    private ArrayList<Integer> preSortedStack = new ArrayList<Integer>();

    public static void main(String args[]){
        Main main = new Main();
    }

    public Main() {
        filename = System.getProperty("file.name");
        print = Boolean.valueOf(System.getProperty("print"));

        int loopNum = 0;

        while(loopNum < 1000000) {
            getStack();
            preSortedStack.addAll(stack);
            Collections.sort(preSortedStack);
            sortStack();
            loopNum++;
        }
    }

    private void sortStack() {

        while(stack.size() > 0) {

            //if(isSorted()) { break;}

            int largestElement = getLargestElement();
            pushElementToBottom(largestElement, print);

            sortedStack.add(stack.get(stack.size()-1));
            stack = new ArrayList<Integer>(stack.subList(0, stack.size() - 1));

        }

        sortedStack.addAll(stack);

        Collections.reverse(sortedStack);
        System.out.println("Done = " + sortedStack.toString());
        System.out.println("flip count = " + flipCount);

    }

    private boolean isSorted() {
        return(stack.equals(preSortedStack));
    }

    private void pushElementToBottom(int largestElement, boolean print) {

        ArrayList<Integer> toFlip = new ArrayList<Integer>(stack.subList(0, largestElement+1));
        Collections.reverse(toFlip);

        ArrayList<Integer> notFlipped = new ArrayList<Integer>(stack.subList((largestElement+1), stack.size()));
        stack = new ArrayList<Integer>(toFlip);
        stack.addAll(notFlipped);

        flipCount++;

        if(print) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.addAll(stack);
            temp.addAll(sortedStack);
            System.out.println(temp);
        }

        Collections.reverse(stack);

        if(print) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            temp.addAll(stack);
            temp.addAll(sortedStack);
            System.out.println(temp);
        }

        flipCount++;
    }

    private int getLargestElement(){

        int biggestNum = 0;
        int biggestElement = 0;
        for(int i = 0; i < stack.size(); i++) {
            if (stack.get(i) > biggestNum) {
                biggestNum = stack.get(i);
                biggestElement = i;
            }
        }

        //Collections.max(stack);

        return biggestElement;
    }

    private ArrayList<Integer> getStack() {

        BufferedReader br = null;
        FileReader fr = null;

        try {

            //br = new BufferedReader(new FileReader(FILENAME));
            fr = new FileReader(filename);
            br = new BufferedReader(fr);


            int length = Integer.parseInt(br.readLine());
            String[] arrayString = br.readLine().split(" ");

            for(String number : arrayString) {
                stack.add(Integer.parseInt(number));
            }


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
                if (fr != null)
                    fr.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return stack;
    }
}
