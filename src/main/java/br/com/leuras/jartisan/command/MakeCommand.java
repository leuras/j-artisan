package br.com.leuras.jartisan.command;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.FileAlreadyExistsException;
import java.util.ArrayList;
import java.util.List;

import org.jtwig.JtwigModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import br.com.leuras.jartisan.enumerator.TemplateEnum;
import br.com.leuras.jartisan.service.ConsoleService;
import br.com.leuras.jartisan.service.PropertiesService;
import br.com.leuras.jartisan.service.TemplateService;
import br.com.leuras.jartisan.util.Constants;

@ShellComponent
public class MakeCommand {
	
	@Autowired
	private ConsoleService svcConsole;
	
	@Autowired
	private PropertiesService svcProperties;
	
	@Autowired
	private TemplateService svcTemplate;

	@ShellMethod("Creates a new controller class")
	public void makeController() {

	}

	@ShellMethod("Creates a new DAO interface and your implementation class")
	public void makeDao(
			@ShellOption(help = "The name of the class") String name,
			@ShellOption(help = "The target entity (model) class") String entity,
			@ShellOption(help = "The name of the base package", defaultValue = ShellOption.NULL) String pkg) {
		
		final JtwigModel variables = this.buildTemplateVariables(name, entity, pkg);
		
		List<TemplateEnum> templates = new ArrayList<>();
		
		templates.add(TemplateEnum.DAO);
		templates.add(TemplateEnum.DAO_IMPL);
		
		for (TemplateEnum tpl : templates) {
			
			try {
				
				File clazz = this.svcTemplate.create(tpl, variables);
				this.svcConsole.write("(+) :> %s", clazz.getPath());
				
			} catch (FileAlreadyExistsException e) {
				this.svcConsole.write("(=) :> %s", e.getFile());
			} catch (FileNotFoundException e) {
				// TODO: handle exception
			}
		}
	}

	@ShellMethod("Create a new service class")
	public void makeService() {

	}
	
	protected JtwigModel buildTemplateVariables(String name, String entity, String pkg) {
		
		JtwigModel variables = JtwigModel.newModel()
				.with(Constants.Variable.ENTITY, entity)
				.with(Constants.Variable.CLASSNAME, name)
				.with(Constants.Variable.PACKAGE, this.svcProperties.defaultPackageIfNullOrEmpty(pkg))
				.with(Constants.Variable.ROOT, this.svcProperties.getDefaultBasePackage());
		
		return variables;
	}
}
