package Less06_MavenPlugin;


import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;

import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Goal which touches a timestamp file.
 */
@Mojo( name = "touch", defaultPhase = LifecyclePhase.PROCESS_SOURCES )
public class MyMojo extends AbstractMojo {
    
    /**
     * Location of the file.
     */
    @Parameter( defaultValue = "${project.build.directory}", property = "outputDir", required = true )
    private File outputDirectory;
    List<String> list;
    
    public void execute() throws MojoExecutionException {
        File f = outputDirectory;

        if (!f.exists()) {
            f.mkdirs();
        }
        File touch = new File(f,"touch.txt");

        FileWriter w = null;
        Path path = Paths.get(String.valueOf(touch));
        try {
            w = new FileWriter(touch, true);
            list = Files.readAllLines(path);
            int count = list.size();
            Date date = new Date();
            
            String time = (count + 1) + ": " + date.toString() + "\n";
            w.write(time);
        } catch (IOException e) {
            
            throw new MojoExecutionException( "Error creating file " + touch, e );
            
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (IOException e) {
                    // ignore
                }
            }
        }
    }
}
