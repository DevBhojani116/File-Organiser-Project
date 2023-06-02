import java.io.File;
import java.util.*;
public class File_Organiser
{
    Scanner sc = new Scanner(System.in);
    int c = 0;//counter variable


    public static void main(String args[])
    {
        File_Organiser f = new File_Organiser();
        f.process();
    } 


    public void process()
    {
        System.out.println("Enter the path of the file on which you want to perform the code");
        String filePath = Path(sc.nextLine().trim());
        System.out.println("Your file path is: " + filePath);
        File folder = new File(filePath);
        File[] contents = folder.listFiles();


        //finding out the number of files in the entered directory
        int numberOfFiles = 0;
        for(File temp:contents)
        {
            if(temp.isDirectory())
                continue;
            System.out.println(temp.getAbsolutePath());
            System.out.println(getExtension(temp.getName()));
            numberOfFiles++;
        }


        String Extensions[] = new String[numberOfFiles];
        String nameOfFile[] = new String[numberOfFiles];
        String FilePath[] = new String[numberOfFiles];


        //finding out the Extension, name and the paths of the files and then storing those into their respective arrays
        c = 0;
        for(File temp:contents)
        {
            if(temp.isDirectory())
                continue;
            Extensions[c] = getExtension(temp.getName());
            nameOfFile[c] = temp.getName();
            FilePath[c] = temp.getAbsolutePath();
            c++;
        }
        System.out.println();


        //displaying the extensions of the files, their names and their paths
        for(int i = 0; i<numberOfFiles; i++)
        {
            System.out.println(Extensions[i]);
            System.out.println(nameOfFile[i]);
            System.out.println(FilePath[i]);
            System.out.println();
        }
        System.out.println();


        //creating directories for different types of files
        for(int i = 0; i<numberOfFiles; i++)
        {
            File temp = new File(filePath+"\\"+Extensions[i]);
            temp.mkdir();
            System.out.println(temp.getAbsolutePath());
        }
        System.out.println();


        //transferring the unsorted files to their respective directories 
        c = 0; 
        for (File temp : contents)
        {
            if(temp.isDirectory()) //won't do anything to existing directories
                continue;
            
            temp.renameTo(new File(filePath + "\\" + Extensions[c] + "\\" + temp.getName()));
            c++;
        }
    }  


    //finds out the extension of the given file
    public String getExtension(String FileName)
    {
        int extensionIndex = FileName.lastIndexOf(".");
        String extension = FileName.substring(extensionIndex);
        return extension;
    }


    //returns the path of the file that can be read by the interpreter, i.e., changing the / to // and removing the ""
    public String Path(String filePath)
    {
        String filePathReadable = "";
        for(int i = 0; i<filePath.length(); i++)
        {
            if(filePath.charAt(i) == '"')
                continue;
            filePathReadable += filePath.charAt(i);
            if(filePath.charAt(i) == '\\')
                filePathReadable += "\\";
        }
        return filePathReadable;
    }
}