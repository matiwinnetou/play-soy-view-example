import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import play.Application;
import play.GlobalSettings;
import spring.PlaySoyViewExampleConfig;

/**
 * Created by mati on 02/02/2014.
 */
public class Global extends GlobalSettings {

    private AnnotationConfigApplicationContext ctx;

    @Override
    public void onStart(final Application app) {
        this.ctx = new AnnotationConfigApplicationContext();
        this.ctx.register(PlaySoyViewExampleConfig.class);
        if (app.isDev()) {
            this.ctx.getEnvironment().setActiveProfiles("dev");
        } else if (app.isTest()) {
            this.ctx.getEnvironment().setActiveProfiles("test");
        } else {
            this.ctx.getEnvironment().setActiveProfiles("prod");
        }

        this.ctx.refresh();
    }

    @Override
    public <A> A getControllerInstance(final Class<A> clazz) {
        return ctx.getBean(clazz);
    }

}

