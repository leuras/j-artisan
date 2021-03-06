package br.com.leuras.jartisan.service;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.util.Map;

import org.jtwig.JtwigModel;
import org.jtwig.JtwigTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.leuras.jartisan.constant.TemplateVariables;
import br.com.leuras.jartisan.enumerator.TemplateTypeEnum;

@Service
public class TemplateService {
	
	@Value("${default.template.folder}")
	private String templatesDirectory;
	
	@Value("${default.output.base.folder}")
	private String baseOutputDirectory;
	
	@Value("${src.folder}")
	private String srcDirectory;
	
	@Value("${test.folder}")
	private String testDirectory;
	
	@Value("${file.extension}")
	private String filenameExtension;
	
	public String generate(final TemplateTypeEnum template, final Map<String, Object> variables) throws FileNotFoundException, FileAlreadyExistsException {
		
		String templatePath = String.format("%s/%s", this.templatesDirectory.trim(), template.getFilename());
		
		File outputFile = this.getOutputFile(template, variables);
		
		if (outputFile.exists()) {
			throw new FileAlreadyExistsException(outputFile.getName());
		}
		
		JtwigTemplate jtwigEngine = JtwigTemplate.classpathTemplate(templatePath);
		jtwigEngine.render(JtwigModel.newModel(variables), new FileOutputStream(outputFile));
		
		return outputFile.getName();
	}
	
	protected File getOutputFile(final TemplateTypeEnum template, final Map<String, Object> variables) {
		
		final StringBuilder outputPath = new StringBuilder(this.baseOutputDirectory.trim());
		
		outputPath.append(File.separator);
		
		//-- concats source folder path
		outputPath.append(this.srcDirectory.trim());
		outputPath.append(File.separator);
		
		//-- converts package into system path
		String pkg = (String) variables.get(TemplateVariables.PACKAGE);
		String fullPackage = pkg.concat(".").concat(template.getSubPackageFragment());
				
		outputPath.append(this.convertPackageIntoSystemPath(fullPackage));
		outputPath.append(File.separator);
		
		final File outputDir = new File(outputPath.toString());
		outputDir.mkdirs();
		
		StringBuilder outputFilename = new StringBuilder(outputPath.toString());
		
		String classname = (String) variables.get(TemplateVariables.CLASSNAME);
		
		outputFilename.append(classname);
		outputFilename.append(this.filenameExtension);
		
		return new File(outputFilename.toString());
	}
	
	protected String convertPackageIntoSystemPath(final String pkg) {
		return pkg.replaceAll("\\.", File.separator);
	}
}
