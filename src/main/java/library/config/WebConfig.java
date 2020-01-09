package library.config;

import library.converter.BookDTOToBookConverter;
import library.converter.BookToBookDTOConverter;
import library.converter.UserToUserDTOConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new UserToUserDTOConverter());
        registry.addConverter(new BookToBookDTOConverter());
        registry.addConverter(new BookDTOToBookConverter());
    }

}
