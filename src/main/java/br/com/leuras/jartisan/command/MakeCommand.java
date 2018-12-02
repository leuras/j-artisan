package br.com.leuras.jartisan.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import br.com.leuras.jartisan.builder.JArtisanMakeCommandBuilderImpl;
import br.com.leuras.jartisan.constant.ShellOptionHelp;
import br.com.leuras.jartisan.constant.TemplateVariables;
import br.com.leuras.jartisan.enumerator.TemplateTypeEnum;

@ShellComponent
public class MakeCommand {

    @Autowired
    private JArtisanMakeCommandBuilderImpl builder;

    @ShellMethod("Creates a new controller class")
    public void makeController(@ShellOption(help = ShellOptionHelp.NAME) String name,
            @ShellOption(help = ShellOptionHelp.ENTITY, defaultValue = ShellOption.NULL) String entity,
            @ShellOption(help = ShellOptionHelp.PACKAGE, defaultValue = ShellOption.NULL) String pkg) {

    }

    @ShellMethod("Creates a new service class")
    public void makeService(@ShellOption(help = ShellOptionHelp.NAME) String name,
            @ShellOption(help = ShellOptionHelp.ENTITY, defaultValue = ShellOption.NULL) String entity,
            @ShellOption(help = ShellOptionHelp.PACKAGE, defaultValue = ShellOption.NULL) String pkg) {

    }

    @ShellMethod("Creates a new DAO interface and your implementation class")
    public void makeDao(@ShellOption(help = ShellOptionHelp.NAME) String name,
            @ShellOption(help = ShellOptionHelp.ENTITY) String entity,
            @ShellOption(help = ShellOptionHelp.PACKAGE, defaultValue = ShellOption.NULL) String pkg) {

        JArtisanMakeCommandImpl command = (JArtisanMakeCommandImpl) this.builder
            .withClassName(name)
            .withClassPackage(pkg)
            .withTargetEntity(entity)
            .withTemplateType(TemplateTypeEnum.DAO)
            .build();
        
        command.run();
        
        final String classImpl = String.format("%sImpl", name);
        
        command = (JArtisanMakeCommandImpl) this.builder
            .withClassName(classImpl)
            .withTemplateType(TemplateTypeEnum.DAOIMPL)
            .build();
        
        command.addVariable(TemplateVariables.INTERFACE, name);
        command.run();
    }
}
