 INSTRUCTIONS FOR USE

 In IntelliJ IDEA:

 1) Import project
    a) Import --> Create project from existing sources --> Browse to arff2libsvm root directory & Next -->
    b) Make sure src directory is located & check, Next --> Hit next until...
    c) Set Project SDK (written using Java 1.8), Next, Next until Finished

 2) Add Weka library
    a) File --> Project Structure... --> Project Settings --> Libraries --> + --> Java
    b) Navigate to your weka.jar file (by default in the root of the Weka directory)

 3) Change the SOURCEDIR variable in arff2libsvm.java to point to the directory containing your .arff files to be converted

 4) Run the project (right-click arff2libsvm --> Run 'arff2libsvm.main()')
