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
            @ShellOption(help = ShellOptionHelp.ENTITY) String entity,
            @ShellOption(help = ShellOptionHelp.PACKAGE, defaultValue = ShellOption.NULL) String pkg,
            @ShellOption(help = ShellOptionHelp.SERVICE, defaultValue = ShellOption.NULL) String service,
            @ShellOption(help = ShellOptionHelp.DAO,     defaultValue = ShellOption.NULL) String dao) {
        
        if (service != null) {
            this.makeService(service, entity, pkg, dao);
            this.builder.withServiceClassName(service);
        }
        
        this.builder
            .withClassName(name)
            .withClassPackage(pkg)
            .withTargetEntity(entity)
            .withTemplateType(TemplateTypeEnum.CONTROLLER)
            .build()
            .run();
    }

    @ShellMethod("Creates a new service class")
    public void makeService(@ShellOption(help = ShellOptionHelp.NAME) String name,
            @ShellOption(help = ShellOptionHelp.ENTITY) String entity,
            @ShellOption(help = ShellOptionHelp.PACKAGE, defaultValue = ShellOption.NULL) String pkg,
            @ShellOption(help = ShellOptionHelp.DAO,     defaultValue = ShellOption.NULL) String dao) {
        
        if (dao != null) {
            this.makeDao(dao, entity, pkg);
            this.builder.withDAOClassName(dao);
        }
        
        this.builder
            .withClassName(name)
            .withClassPackage(pkg)
            .withTargetEntity(entity)
            .withTemplateType(TemplateTypeEnum.SERVICE)
            .build()
            .run();
    }

    @ShellMethod("Creates a new DAO interface and your implementation class")
    public void makeDao(@ShellOption(help = ShellOptionHelp.NAME) String name,
            @ShellOption(help = ShellOptionHelp.ENTITY) String entity,
            @ShellOption(help = ShellOptionHelp.PACKAGE, defaultValue = ShellOption.NULL) String pkg) {

        this.builder
            .withClassName(name)
            .withClassPackage(pkg)
            .withTargetEntity(entity)
            .withTemplateType(TemplateTypeEnum.DAO)
            .build()
            .run();
        
        this.builder
            .withClassName(String.format("%sImpl", name))
            .withTemplateType(TemplateTypeEnum.DAOIMPL)
            .withVar(TemplateVariables.INTERFACE, name)
            .build()
            .run();        
    }
}
