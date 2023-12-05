package com.bezkoder.spring.security.jwt.security.services.mailService;

import java.time.LocalDate;

import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Service
public class ThymeleafService {
	private static final String MAIL_TEMPLATE_BASE_NAME = "mail/MailMessages";
    private static final String MAIL_TEMPLATE_PREFIX = "/templates/";
    private static final String MAIL_TEMPLATE_SUFFIX = ".html";
    private static final String UTF_8 = "UTF-8";

    private static final String TEMPLATE_NAME = "reservation";

    private static TemplateEngine templateEngine;

    static {
        templateEngine = emailTemplateEngine();
    }

    private static TemplateEngine emailTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(htmlTemplateResolver());
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        return templateEngine;
    }

    private static ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename(MAIL_TEMPLATE_BASE_NAME);
        return messageSource;
    }

    private static ITemplateResolver htmlTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setPrefix(MAIL_TEMPLATE_PREFIX);
        templateResolver.setSuffix(MAIL_TEMPLATE_SUFFIX);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(UTF_8);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    public String setContent(String patientname, String patientemail, String patientsdt, String doctorname, String doctoremail,
    		String doctorsdt, String banknumber,String bankname, String time,String date, String price) {
        final Context context = new Context();

        context.setVariable("patientname", patientname);
        context.setVariable("patientemail", patientemail);
        context.setVariable("patientsdt", patientsdt);
        context.setVariable("doctorname", doctorname);
        context.setVariable("doctoremail", doctoremail);
        context.setVariable("doctorsdt", doctorsdt);
        context.setVariable("banknumber", banknumber);
        context.setVariable("bankname", bankname);
        context.setVariable("time", time);
        context.setVariable("date", date);
        context.setVariable("price", price);

        return templateEngine.process(TEMPLATE_NAME, context);
    }
}
