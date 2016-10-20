package edu.gatech.cs7641.jmcmahon;

import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.LibSVMSaver;

import java.io.File;
import java.io.FilenameFilter;


/* Jonathan E. McMahon (2016) No rights reserved */


// INSTRUCTIONS
//
// In IntelliJ IDEA:
// 1) Import project
//    a) Import --> Create project from existing sources --> Browse to arff2libsvm root directory & Next -->
//    b) Make sure src directory is located & check, Next --> Hit next until...
//    c) Set Project SDK (written using Java 1.8), Next, Next until Finished
// 2) Add Weka library
//    a) File --> Project Structure... --> Project Settings --> Libraries --> + --> Java
//    b) Navigate to your weka.jar file (by default in the root of the Weka directory)
// 3) Change the SOURCEDIR variable to point to the directory containing your .arff files to be converted
// 4) Run the project (right-click arff2libsvm --> Run 'arff2libsvm.main()')

/**
 * Converts all the .arff files in a directory (specified by SOURCEDIR)
 * into .libsvm format using the Weka library's LibSVMSaver class.
 * This will not alter the .arff files, but saves new files with a .libsvm extension.
 */
public class arff2libsvm {

    // SET THE SOURCE DIRECTORY CONTAINING YOUR ARFF FILES:
    public static final String SOURCEDIR = "/full/path/to/arff/files/"; // must have trailing '/'

    public static void main(String[] args) throws Exception {

        String sourceDir = SOURCEDIR;

        // Set up filter to select just arff files
        FilenameFilter arffFilter = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".arff")) {
                    return true;
                } else {
                    return false;
                }
            }
        };

        File[] files = new File(sourceDir).listFiles(arffFilter);
        for (File file : files) {
            if (file.isFile()) {
                String filenameWithoutExtension = file.getName().replaceFirst("[.][^.]+$", "");
                convertFile(file, new File(sourceDir+filenameWithoutExtension+ ".libsvm"));
            }
        }
    }


    public static void convertFile(File arfffile, File datfile) throws Exception {

        // Load ARFF file
        ArffLoader loader = new ArffLoader();
        loader.setSource(arfffile);
        Instances data = loader.getDataSet();

        // Save libsvm file
        LibSVMSaver saver = new LibSVMSaver();
        saver.setInstances(data);
        saver.setFile(datfile);
        saver.setDestination(datfile);
        saver.writeBatch();

    }

}
