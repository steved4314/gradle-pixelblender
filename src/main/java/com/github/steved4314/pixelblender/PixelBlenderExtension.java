package com.github.steved4314.pixelblender;

import org.gradle.api.Project;
import org.gradle.api.file.DirectoryProperty;
import org.gradle.api.internal.FilePropertyContainer;
import org.gradle.api.provider.Property;

import java.io.File;

public class PixelBlenderExtension {
    public DirectoryProperty workingDirectory;

    public DirectoryProperty pixelBlenderToolkit;
    public Property<String> pixelBlenderToolkitUrl;

    public PixelBlenderExtension(Project project) {
        this.workingDirectory = project.getObjects().directoryProperty();
        this.workingDirectory.set(new File(project.getBuildDir(), "pixelblender"));

        this.pixelBlenderToolkit = project.getObjects().directoryProperty();

        this.pixelBlenderToolkitUrl = project.getObjects().property(String.class);
        this.pixelBlenderToolkitUrl.set("http://www.adobe.com/go/pixelbender_toolkit_zip/");
    }
}
