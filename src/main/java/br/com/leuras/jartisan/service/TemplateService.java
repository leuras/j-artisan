package br.com.leuras.jartisan.service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.FileAlreadyExistsException;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.leuras.jartisan.enumerator.TemplateEnum;
import br.com.leuras.jartisan.util.Constants;

@Service
public class TemplateService {
	
	private static final String PATH_FORMAT = "%s/%s";
	
	@Value("${default.template.folder}")
	private String templatesDirectory;
	
	@Value("${default.output.base.folder}")
	private String baseOutputDirectory;
	
	public File create(final TemplateEnum template, final JtwigModel variables) throws FileNotFoundException, FileAlreadyExistsException {
		
		String templatePath = String.format(PATH_FORMAT, this.templatesDirectory.trim(), template.getFilename());
		
		String name = (String) variables.get(Constants.Variable.CLASSNAME).get().getValue();
		String pkg  = (String) variables.get(Constants.Variable.PACKAGE).get().getValue();
		
		File outputFile = this.getOutputFile(template, pkg, name);
		
		if (outputFile.exists()) {
			throw new FileAlreadyExistsException(outputFile.getPath());
		}
		
		JtwigTemplate jtwigEngine = JtwigTemplate.classpathTemplate(templatePath);
		jtwigEngine.render(variables, new FileOutputStream(outputFile));
		
		return outputFile;
	}
	
	protected File getOutputFile(final TemplateEnum template, final String pkg, final String name) {
		
		StringBuilder outputDirName = new StringBuilder(this.baseOutputDirectory.trim());
		
		outputDirName.append(File.separator);
		
		//-- concats java source folder path
		outputDirName.append(String.format(Constants.FileSystem.SRC_DIR, File.separator, File.separator, File.separator));
		outputDirName.append(File.separator);
		
		//-- converts package into system path
		String fullPackage = pkg.concat(template.getSubPackageFragment());
		String regExp = String.format("\\%s", Constants.File.PACKAGE_SEPARATOR);
				
		outputDirName.append(fullPackage.replaceAll(regExp, File.separator));
		outputDirName.append(File.separator);
		
		File outputDir = new File(outputDirName.toString());
		outputDir.mkdirs();
		
		StringBuilder outputFilename = new StringBuilder(outputDirName.toString());
		
		outputFilename.append(name);
		outputFilename.append(Constants.File.EXTENSION);
		
		return new File(outputFilename.toString());
	}

}
