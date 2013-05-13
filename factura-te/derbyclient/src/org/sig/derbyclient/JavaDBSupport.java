package org.sig.derbyclient;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.execution.NbProcessDescriptor;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.util.Utilities;

public class JavaDBSupport {

    public static final String JAVADB_HOME = "javadb.home";
    public static final String JAVADB_PROPERTIES_HOME = "javadb.properties.home";

    public static void ensureStartedDB() {
        try {
            startDB();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
    }

    private static void startDB() throws IOException {
        String java = null;
        File javaExecuble = new File(System.getProperty("java.home"), "/bin/java" + (Utilities.isWindows() ? ".exe" : "")); // NOI18N
        if (javaExecuble != null && javaExecuble.exists()) {
            if (javaExecuble.canExecute()) {
                java = javaExecuble.getAbsolutePath();
            }
        }
        if (java == null) {
            // XXX: MessageBox
        }

        if (getDerbyInstallation() == null) {
            // XXX: MessageBox
        }

        // java -Dderby.system.home="<userdir/derby>" -classpath
        //     "<DERBY_INSTALL>/lib/derby.jar:<DERBY_INSTALL>/lib/derbytools.jar:<DERBY_INSTALL>/lib/derbynet.jar"
        //     org.apache.derby.drda.NetworkServerControl start
        NbProcessDescriptor desc = new NbProcessDescriptor(
                java,
                "-Dderby.system.home=\"" + getDerbySystemHome() + "\" "
                + "-classpath \"" + getNetworkServerClasspath() + "\""
                + " org.apache.derby.drda.NetworkServerControl start");
        Logger.getLogger(JavaDBSupport.class.getName()).log(Level.FINE, "Running {0} {1}", new String[]{desc.getProcessName(), desc.getArguments()});
        Process process = desc.exec(null, getEnvironment(), true, getDerbyInstallation());
        if (process == null) {
            Logger.getLogger(JavaDBSupport.class.getName()).log(Level.FINE, "Process {0} is running.", new Object[]{process});
        } else {
            Logger.getLogger(JavaDBSupport.class.getName()).log(Level.FINE, "Process didn't start.");
        }
    }

    private static String getDerbySystemHome() {
        String home = null;
        home = System.getProperty(JAVADB_PROPERTIES_HOME);
        if (home == null || home.length() == 0) {
            // path to the default place of Java DB server in NB IDE
            home = System.getProperty("user.home") + "/.netbeans-derby/";
        }
        return home;
    }

    private static String getNetworkServerClasspath() {
        File f = getDerbyInstallation();
        if (f == null) {
            throw new IllegalStateException("No JavaDB installation found.");
        }
        return new File(f, "lib/derby.jar").getAbsolutePath() + File.pathSeparator
                + new File(f, "lib/derbytools.jar").getAbsolutePath() + File.pathSeparator
                + new File(f, "lib/derbynet.jar").getAbsolutePath();
    }

    private static File getDerbyInstallation() {
        File f = null;
        String javaDBHome = System.getProperty(JAVADB_HOME);
        if (javaDBHome == null) {
            javaDBHome = NbBundle.getMessage(JavaDBSupport.class, JAVADB_HOME);
            if (javaDBHome != null) {
                f = new File(javaDBHome);
            } else {
                String javaHome = System.getProperty("java.home");
                // path to JavaDB in JDK6
                f = new File(javaHome + "/../db/");
            }
        } else {
            f = new File(javaDBHome);
        }
        return f != null && f.exists() ? f : null;
    }

    private static String[] getEnvironment() {
        String location = getDerbyInstallation().getAbsolutePath();
        if (location.equals("")) { // NOI18N
            return null;
        }
        return new String[]{"DERBY_INSTALL=" + location}; // NOI18N
    }
}
