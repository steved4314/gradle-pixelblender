package com.github.steved4314.pixelblender;

import de.undercouch.gradle.tasks.download.Download;
import de.undercouch.gradle.tasks.download.DownloadTaskPlugin;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.tasks.Copy;
import org.gradle.api.tasks.TaskProvider;

public class PixelBlenderPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        // Rely on download task plugin
        project.getPluginManager().apply(DownloadTaskPlugin.class);

        PixelBlenderExtension extension = project.getExtensions().create("pixelblender", PixelBlenderExtension.class);

        // Download the toolkit zip
        TaskProvider<Download> downloadToolkitZip = project.getTasks().register("downloadPixelBlenderToolkitInstaller", Download.class);
        downloadToolkitZip.configure(task -> {
                    task.src(extension.pixelBlenderToolkitUrl);
                    task.dest(extension.workingDirectory.file("toolkitInstaller/toolkit.zip"));
                });

        project.getTasks().register("unzipPixelBlenderToolkitInstaller", Copy.class)
                .configure(task -> {
                    task.from(project.zipTree(downloadToolkitZip.get().getDest()));
                    task.into(extension.workingDirectory.dir("toolkitInstaller"));
                });

        // Extract pbutil.exe from the toolkit installer
        project.getTasks().register("installPixelBlenderToolkit").configure(task -> {

        });
    }
}
