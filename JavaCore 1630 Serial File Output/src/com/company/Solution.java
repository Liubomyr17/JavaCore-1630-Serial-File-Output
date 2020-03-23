package com.company;

/*

1630 Serial File Output

1. Understand what the program does.
2. In the static block, read 2 file names firstFileName and secondFileName.
3. Inside the Solution class, create a public static ReadFileThread thread that implements
ReadFileInterface interface (Think of which is more suitable - Thread or Runnable).
3.1. The setFileName method must set the name of the file from which the contents will be read.
3.2. The getFileContent method should return the contents of the file.
3.3. In the run method, read the contents of the file, close the stream. Separate the lines of the file with a space.
4. Think about where you need to wait for the thread to finish working to ensure consistent file output.
4.1. To do this, add a call to the corresponding method.
Expected Conclusion:
[whole body of the first file]
[whole body of the second file]

Requirements:
1. The static block of the Solution class should read the names of two files from the console and save them in the variables firstFileName and secondFileName.
2. Declare the ReadFileThread class in the Solution public static class.
3. The ReadFileThread class must implement the ReadFileInterface interface.
4. The ReadFileThread class must be inherited from a suitable class.
5. The run method of the ReadFileThread class should read lines from the file set by the setFileName method. And the getFileContent method, of the same class, should return the read content. The return value is a single line consisting of lines in the file, separated by spaces.
6. The systemOutPrintln method should call the join method on the created f object.
7. The output of the program should consist of 2 lines. Each line is the contents of one file.


 */



import java.io.*;
import java.util.ArrayList;
public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    //add your code here - добавьте код тут
    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        //add your code here - добавьте код тут
        f.join();
        System.out.println(f.getFileContent());

    }

    public interface ReadFileInterface {

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    //add your code here - добавьте код тут
    public static class ReadFileThread extends Thread implements ReadFileInterface {
        private String fileName;
        private ArrayList<String> content;

        public ReadFileThread() {
            this.fileName = null;
            this.content = new ArrayList<>();
        }

        public void run() {
            try {
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
                while (fileReader.ready()) {
                    content.add(fileReader.readLine());
                }
                fileReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File "+fileName+" not found");
            } catch (IOException e) {
                System.out.println("File "+fileName+" can't read");
            }

        }

        @Override
        public void setFileName(String fullFileName) {
            this.fileName = fullFileName;
        }

        @Override
        public String getFileContent() {
            StringBuffer sbuf = new StringBuffer();
            for (String s : content) {
                sbuf.append(s).append(" ");
            }
            return sbuf.toString();
        }
    }
}








