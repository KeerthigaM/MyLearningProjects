package dependecnyInjectionByAnnotation;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainAnnotationApplication {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DIConfiguration.class);
		MyAnnotationApplication app = (MyAnnotationApplication) context.getBean(MyAnnotationApplication.class);
		app.processMessage("Hi Kirthi", "Kirthi@abc.com");
		context.close();
	}
}
